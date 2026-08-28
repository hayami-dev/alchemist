/**
 * ツール（道具）アイテム。
 * マテリアルアイテムのvalue値をいくつ変化させるかの係数を保持する。
 */

package com.example.app.domain;

import com.example.app.domain.enums.ToolEffectType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ToolItem extends Item {
  private double efficiency; // マテリアルアイテムのvalueを変化させる係数
  private ToolEffectType toolEffectType; // ツールアイテムの効果種別
}
