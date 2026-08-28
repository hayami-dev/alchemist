/**
 * インベントリ。
 * 手持ちアイテムの一覧を保持する。
 */

package com.example.app.domain;

import lombok.Data;

@Data
public class InventoryItem {
  private Item item; // MaterialItem, ToolItemいずれかを呼び出す
  private int quantity;// 持っている数
}
