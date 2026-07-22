/**
 * アイテムの基底クラス。
 * 全アイテム共通のプロパティ（ID、名前、価格など）を保持する。
 */

package com.example.app.domain;

import com.example.app.domain.enums.ItemType;

import lombok.Data;

@Data
public class Item {
  private Long id; // アイテムID
  private String name; // アイテム名
  private ItemType itemType; // アイテムの種類 TODO:素材アイテムにできるかはここで判断する
  private String description; // アイテムの解説
  private int buyPrice; // ショップ購入価格
  private int sellPrice; // ショップ売却価格
  private boolean canSell; // 売却可能フラグ

}
