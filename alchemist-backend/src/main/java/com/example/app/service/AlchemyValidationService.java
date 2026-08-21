/**
 * AlchemyServiceから分割したバリデーションに専念したサービス。
 * TODO: 設計書に追加
 */
package com.example.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.app.domain.Item;
import com.example.app.domain.enums.CalculationType;
import com.example.app.domain.enums.ItemType;
import com.example.app.exception.InvalidCraftException;
import com.example.app.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlchemyValidationService {

  // materialIdsからItemクラスを呼び出す用
  private final ItemMapper itemMapper;

  // 選択された素材が2つ以上あるかを判定する。
  // TODO:設計書に追記
  public boolean hasEnoughSelectedMaterials(List<Long> materialIds) {
    return materialIds.size() >= 2;
  }

  // 素材数と計算方法の数が一致しているかを判定する。
  // (素材N個→計算方法N-1個)
  // TODO:設計書に追記
  public boolean isSlotCountMatching(
      List<Long> materialIds, List<CalculationType> calcTypes) {
    return calcTypes.size() == materialIds.size() - 1;
  }

  // ItemTypeが「MATERIAL」のみ許可するバリデーション。
  public boolean isMaterialItem(List<Item> items) {
    return items.stream().allMatch(item -> item.getItemType() == ItemType.MATERIAL);
  }

  /**
   * TODO：以下、インベントリ・カタログ機能実装時に合わせて実装
   */
  // 選んだ選択方法がインベントリの道具で解放済みかを判定する。
  // TODO:設計書に追記
  public boolean hasUnlockCalcItem() {
    return false;
  }

  // 調合素材が足りるかどうかを判定する。
  // TODO: 設計書を修正
  // TODO: フロント設計書に追記
  public boolean hasEnoughInventoryMaterials(Long playerId, Long recipeId) {
    return false;
  }

  // 新しい調合レシピかどうかを判定する。
  // TODO: フロント設計書に追記
  public boolean isNewRecipe(Long playerId, Long recipeId) {
    return false;
  }

  // 調合結果が素材アイテムと同じアイテムではない場合のみ許可するバリデーション。
  // 1つも一致しなかったらtrueが返る
  // TODO: フロント設計書に追記
  public boolean isDifferentResult(Long itemId, List<Long> materialItemIds) {
    return materialItemIds.stream().noneMatch(miId -> Objects.equals(miId, itemId));
  }

  /* AlchemyServiceで呼び出す調合時のバリデーションメソッド群 */
  public void validateCraftItem(
      List<Long> materialIds,
      List<CalculationType> calcTypes,
      Long playerId) {
    List<Item> items = new ArrayList<>();
    for (Long materialId : materialIds) {
      Item item = itemMapper.findById(materialId);
      items.add(item);
    }

    if (!hasEnoughSelectedMaterials(materialIds)) {
      throw new InvalidCraftException("素材を2つ以上選んでください。");
    }
    if (!isSlotCountMatching(materialIds, calcTypes)) {
      throw new InvalidCraftException("調合素材と計算方法の数が一致しません。");
    }
    if (!isMaterialItem(items)) {
      throw new InvalidCraftException("素材アイテム以外のアイテムが含まれています。");
    }
  }
}
