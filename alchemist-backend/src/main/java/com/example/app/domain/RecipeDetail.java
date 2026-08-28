/**
 * レシピ詳細。
 * 調合時の順番の番号、アイテム情報、計算方法を保持する。
 */
package com.example.app.domain;

import com.example.app.domain.enums.CalculationType;

import lombok.Data;

@Data
public class RecipeDetail {

  private Long id;// レシピid
  private int stepOrder;// 調合式の何番目かの番号
  private Item item; // マテリアルアイテムid
  private CalculationType calculationsType;// 計算方法
}
