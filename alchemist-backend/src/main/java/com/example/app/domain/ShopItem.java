/**
 * ショップに並ぶアイテム。
 * Itemクラスと、その個数を保持する。
 */
package com.example.app.domain;

import lombok.Data;

@Data
public class ShopItem {
  private Item item; // どのアイテムか（MaterialItem, ToolItemいずれかを呼び出す）
  private int quantity; // 持っている数

}
