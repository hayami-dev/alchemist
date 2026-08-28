/**
 * マテリアル（素材）アイテム。
 * Itemクラスを基底に持ち、レアリティや調合時の値を保持する。
 */

package com.example.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialItem extends Item {
  private int rarityId;// レアリティの枠
  private int starRank; // ★の数（1〜5の数値）
  private int value; // 調合時に計算する数値

}
