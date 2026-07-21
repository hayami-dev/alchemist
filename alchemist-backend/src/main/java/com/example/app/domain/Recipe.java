/**
 * カタログとレシピ詳細を紐づけるの中間テーブル。
 * アイテム情報とレシピ詳細のリストを保持する。
 */

package com.example.app.domain;

import java.util.List;

import lombok.Data;

@Data
public class Recipe {
  private Long id; // レシピid
  private Long resultItemId; // 出来上がるアイテムのid
  private List<RecipeDetail> detail;// 調合方法のリスト
}
