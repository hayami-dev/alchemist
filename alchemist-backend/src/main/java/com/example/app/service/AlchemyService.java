package com.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.InventoryItem;
import com.example.app.domain.Item;
import com.example.app.domain.ToolItem;
import com.example.app.domain.enums.CalculationType;
import com.example.app.domain.enums.ItemType;
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

  // 調合結果のアイテム情報を取得する。
  public Item getAlchemyItem(Long itemId) {
    return null;
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
  // TODO: 設計書に追加
  public List<CalculationType> getUnlockedCalcTypes(Long playerId) {
    List<InventoryItem> items = inventoryMapper.findAll(playerId);

    List<CalculationType> available = new ArrayList<>();
    available.add(CalculationType.ADD);

    for (InventoryItem inv : items) {
      Item item = inv.getItem();
      if (item instanceof ToolItem toolItem) {
        CalculationType unlocked = toolItem.getToolEffectType().toUnlockedCalculationType();
        System.out.println("unlocked" + unlocked);
        if (unlocked != null) {
          available.add(unlocked);
        }
      }
    }

    return available;
  }

  // マテリアルアイテムの値を計算する。渡された計算のタイプによって
  // additionMaterial, multiplyMaterial, moduloMaterialいずれかを走らせる。
  public int calcMaterialValue(Long recipeId) {
    return 0;
  }

  // 渡された二つの値を足し算する。
  public int additionMaterial(int val1, int val2) {
    return 0;
  }

  // 渡された二つの値を掛け算する。
  public int multiplyMaterial(int val1, int val2) {
    return 0;
  }

  // 渡された二つの値を割り算し、余りを出す。
  public int moduloMaterial(int val1, int val2) {
    return 0;
  }

  // InventoryService.addItem を呼び出し、調合によって完成したアイテムを
  // プレイヤーのインベントリ（カバン）に追加する。
  public void addItem(Long playerId, Long itemId, int qty) {

  }

  // InventoryService.consumeItemを呼び出し、
  // 調合の素材にしたアイテムをインベントリから削除（update）する。
  public void consumeUsedMaterials(Long playerId, Long recipeId) {

  }

  // 調合素材が足りるかどうかを判定する。
  public boolean hasEnoughMaterials(Long playerId, Long recipeId) {
    return false;
  }

  // 新しい調合レシピかどうかを判定する。
  public boolean isNewRecipe(Long playerId, Long recipeId) {
    return false;
  }

  // ItemTypeが「MATERIAL」のみ許可するバリデーション。
  public boolean isMaterialItem(List<Item> items) {
    return false;
  }

  // 調合結果が素材アイテムと同じアイテムではない場合のみ許可するバリデーション。
  public boolean isDefferentResult(Long itemId, List<Long> materialItemIds) {
    return false;
  }

  // 調合結果のvalueが負の値だった場合、valueを正の値に変換して返す。
  public boolean normalizeToPositive(int itemValue) {
    return false;
  }

}
