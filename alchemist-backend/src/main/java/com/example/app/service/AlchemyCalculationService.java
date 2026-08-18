/**
 * AlchemyServiceから分割した調合の計算に専念したサービス。
 * TODO: 設計書に追加
 */
package com.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Item;
import com.example.app.domain.MaterialItem;
import com.example.app.domain.enums.CalculationType;
import com.example.app.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlchemyCalculationService {

  private final ItemMapper itemMapper;

  // マテリアルアイテムの値を計算する。渡された計算のタイプによって
  // additionMaterial, multiplyMaterial, moduloMaterialいずれかを走らせる。
  // TODO:設計書を修正
  public int calcMaterialValue(List<Long> materialIds, List<CalculationType> calcTypes) {
    // 素材一覧を作成
    List<Item> materials = new ArrayList<>();
    for (Long materialId : materialIds) {
      materials.add(itemMapper.findById(materialId));
    }

    // 1つ目の素材からスタートさせる
    int running = getValueOf(materials.get(0));

    for (int i = 0; i < calcTypes.size(); i++) {
      int nextValue = getValueOf(materials.get(i + 1));
      CalculationType calcType = calcTypes.get(i);

      running = switch (calcType) {
        case ADD -> additionMaterial(running, nextValue);
        case MULTIPLY -> multiplyMaterial(running, nextValue);
        case MODULO -> moduloMaterial(running, nextValue);
      };
    }

    return normalizeToPositive(running);
  }

  // 渡された二つの値を足し算する。
  public int additionMaterial(int val1, int val2) {
    return val1 + val2;
  }

  // 渡された二つの値を掛け算する。
  public int multiplyMaterial(int val1, int val2) {
    return val1 * val2;
  }

  // 渡された二つの値を割り算し、余りを出す。
  public int moduloMaterial(int val1, int val2) {
    return val1 % val2;
  }

  // 調合結果のvalueが負の値だった場合、valueを正の値に変換して返す。
  // TODO:設計書を修正
  public int normalizeToPositive(int itemValue) {
    return Math.abs(itemValue);
  }

  // MaterialItemからvalueを取り出す
  // TODO:設計書に追加
  public int getValueOf(Item item) {
    if (item instanceof MaterialItem materialItem) {
      return materialItem.getValue();
    }
    return 0;
  }
}
