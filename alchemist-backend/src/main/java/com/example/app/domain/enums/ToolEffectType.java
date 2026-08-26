package com.example.app.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ToolEffectType {
  EFFICIENCY,
  UNLOCK_ADD,
  UNLOCK_MULTIPLY,
  UNLOCK_MODULO;

  // このToolEffectTypeがどのCalculationTypeを解放するかを返す
  // 該当しない場合はnullを返す
  public CalculationType toUnlockedCalculationType() {
    return switch (this) {
      case UNLOCK_ADD -> CalculationType.ADD;
      case UNLOCK_MULTIPLY -> CalculationType.MULTIPLY;
      case UNLOCK_MODULO -> CalculationType.MODULO;
      default -> null;
    };
  }
}
