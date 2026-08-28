/**
 * 調合の実行に関するサービス。
 * アイテム、インベントリの取得および、
 * 調合に関する計算、バリデーションサービスのフィールドを保持する。
 */
package com.example.app.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.app.controller.dto.CraftRequest;
import com.example.app.domain.InventoryItem;
import com.example.app.domain.Item;
import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeDetail;
import com.example.app.domain.ToolItem;
import com.example.app.domain.enums.CalculationType;
import com.example.app.domain.enums.ItemType;
import com.example.app.exception.InvalidCraftException;
import com.example.app.mapper.AlchemyMapper;
import com.example.app.mapper.InventoryMapper;
import com.example.app.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlchemyService {

  // 調合結果のアイテムを1件返す用
  private final ItemMapper itemMapper;
  // 手持ちアイテム一覧を表示させる用
  private final InventoryMapper inventoryMapper;
  private final InventoryService inventoryService;
  // 調合の計算に関するサービス
  private final AlchemyCalculationService alchemyCalculationService;
  // 調合のバリデーションに関するサービス
  private final AlchemyValidationService alchemyValidationService;
  // 調合値からitemIdを取得する用
  private final AlchemyMapper alchemyMapper;

  // 調合結果の取得に使う定数
  private static final int MAX_VALID_VALUE = 100;
  private static final Long BURNT_ITEM_ID = 68L; // 101以上
  private static final Long ZERO_RESULT_ITEM_ID = 31L; // 0の時の固定アイテム
  private static final Long ONE_RESULT_ITEM_ID = 32L; // 1の時の固定アイテム

  /* Controllerから呼ばれる調合実行の本体 */

  // 調合を実行する。
  // 調合結果のアイテム情報をcontrollerへ返す。
  public Item craftItem(CraftRequest request, Long playerId) {
    List<Long> materialIds = request.getMaterialIds();
    List<CalculationType> calcTypes = request.getCalcTypes();

    // 渡された素材、調合方法のバリデーションを実行
    alchemyValidationService.validateCraftItem(materialIds, calcTypes, playerId);

    // 計算を実行
    int resultItemValue = alchemyCalculationService.calcMaterialValue(materialIds, calcTypes);

    // Itemクラスを1件返す
    Item resultItem = getResultItem(resultItemValue);

    // 返ってきたItemクラスが意味のある結果かバリデーションを実行
    if (!alchemyValidationService.isDifferentResult(resultItem.getId(), materialIds)) {
      throw new InvalidCraftException("素材と同じアイテムが\n調合結果になりました・・・");
    }

    // 素材を消費し、完成品をインベントリへ追加する
    consumeUsedMaterials(playerId, materialIds);
    addItem(playerId, resultItem.getId(), 1);

    return resultItem;
  }

  // マテリアルアイテムを選択するためのインベントリを表示させる。
  // TODO: DEBUG 手持ちアイテムはいったん無制限の全アイテムにする
  public List<InventoryItem> getInventory(Long playerId) {
    List<InventoryItem> materials = inventoryMapper.findAll(playerId).stream()
        .filter(item -> item.getItem().getItemType() == ItemType.MATERIAL).toList();
    return materials;
  }

  // 調合方法を一覧を取得する。
  // インベントリをチェックし、該当アイテムによって返す値を調整する。
  public List<CalculationType> getUnlockedCalcTypes(Long playerId) {
    List<InventoryItem> items = inventoryMapper.findAll(playerId);

    Set<CalculationType> available = new LinkedHashSet<>();
    available.add(CalculationType.ADD);

    for (InventoryItem inv : items) {
      Item item = inv.getItem();
      if (item instanceof ToolItem toolItem) {
        CalculationType unlocked = toolItem.getToolEffectType().toUnlockedCalculationType();
        if (unlocked != null) {
          available.add(unlocked);
        }
      }
    }

    return new ArrayList<>(available);
  }

  // resultItemValueの値によって返すitemIdを計算する
  public Item getResultItem(int resultItemValue) {
    if (resultItemValue > MAX_VALID_VALUE) {
      return itemMapper.findById(BURNT_ITEM_ID);
    }
    if (resultItemValue == 0) {
      return itemMapper.findById(ZERO_RESULT_ITEM_ID);
    }
    if (resultItemValue == 1) {
      return itemMapper.findById(ONE_RESULT_ITEM_ID);
    }

    Long itemId = alchemyMapper.getCraftItemId(resultItemValue);
    return itemMapper.findById(itemId);
  }

  // InventoryService.addItem を呼び出し、調合によって完成したアイテムを
  // プレイヤーのインベントリ（カバン）に追加する。
  public void addItem(Long playerId, Long itemId, int qty) {
    inventoryService.addItem(playerId, itemId, qty);
  }

  // InventoryService.consumeItemを呼び出し、
  // 調合の素材にしたアイテムをインベントリから消費（削除・update）する。
  // 同じ素材が複数スロットで選ばれていた場合、まとめて数量分を消費する。
  public void consumeUsedMaterials(Long playerId, List<Long> materialIds) {
    Map<Long, Long> counts = materialIds.stream()
        .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

    counts.forEach((itemId, count) -> inventoryService.consumeItem(playerId, itemId, count.intValue()));
  }

}
