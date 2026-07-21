/**
 * プレイヤー情報。
 * ゲーム内で使用する通過、インベントリ、カタログなどの情報を保持する。
 */

package com.example.app.domain;

import lombok.Data;

@Data
public class Player {
  private Long id; // プレイヤーID
  private String name; // プレイヤーネーム
  private int money; // ゲーム内通貨の所持数
  private Long inventoryId; // 手持ちアイテム（Inventory）のリストID
  private Long catalogId; // アイテム図鑑（Catalog）のリストID
  private int progressRate; // カタログの収集率 TODO:ここがdouble型の方がいいのか？

}
