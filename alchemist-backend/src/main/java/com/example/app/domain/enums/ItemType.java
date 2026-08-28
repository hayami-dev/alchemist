/**
 * アイテムタイプ。
 * 調合の素材にできるアイテムか、道具としてのアイテムかのパターンを保持する。
 */

package com.example.app.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemType {
  MATERIAL, // マテリアルアイテム（調合アイテム）
  TOOL, // ツールアイテム（効果を持った道具アイテム）
  LOOK_ITEM,// 素材にできないアイテム
}
