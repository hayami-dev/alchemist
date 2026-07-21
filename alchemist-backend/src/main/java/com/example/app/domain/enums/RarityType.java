/**
 * マテリアルアイテムのレアリティタイプ。
 * n倍の数かどうかにより割り当てられるテキストを保持する。
 */

package com.example.app.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RarityType {
  COMMON, // コモン（標準的な素材）
  RARE, // レア（希少な素材）
  EPIC, // エピック（非常に珍しい最高峰の素材）
  FANTASIA, // ファンタジア（伝説級の超希少素材）
  STIGMA// スティグマ（呪いを持った暗黒素材）
}
