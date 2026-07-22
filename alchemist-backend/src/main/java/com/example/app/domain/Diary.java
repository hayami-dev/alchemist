/**
 * 日記（ストーリー）のマスタ。
 * 全ストーリーを保持する。
 */

package com.example.app.domain;

import lombok.Data;

@Data
public class Diary {
  private Long id; // 日記id
  private String text; // 日記本文
}
