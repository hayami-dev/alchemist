/**
 * レシピ（カタログ）画面の枠表示用クラス。
 * アイテムの情報と、解放済みかを保持する。
 * TODO:設計書反映
 */
package com.example.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogSlot {
  private Item item;
  private boolean isCataloged;

  public boolean isCataloged() {
    return isCataloged;
  }
}
