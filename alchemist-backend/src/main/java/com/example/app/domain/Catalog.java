/**
 * カタログ。
 * 各アイテム、レシピ、メモなどを保持する。
 */

package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Catalog {

  private Long id;// カタログid
  private Long playerId;// プレイヤーid
  private Item item;// Itemクラスを呼び出す
  private Recipe recipe;// Recipeクラスを呼び出す
  private String memo;// 図鑑等に表示・保存するための素材メモ
  private LocalDateTime unlockedAt;// アンロックされた日付

}
