/**
 * ツール（道具）アイテム。
 * マテリアルアイテムのvalue値をいくつ変化させるかの係数を保持する。
 */

package com.example.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ToolItem extends Item {
  private double efficiency; // マテリアルアイテムのvalueを変化させる係数
}
