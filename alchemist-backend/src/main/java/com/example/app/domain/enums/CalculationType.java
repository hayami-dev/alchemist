/**
 * 計算タイプ。
 * マテリアルアイテムのvalue値をどう処理するかのパターンを保持する。
 */

package com.example.app.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CalculationType {
  ADD, // 足し算
  MULTIPLY, // 掛け算
  MODULO // 割ったあまり

}
