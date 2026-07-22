/**
 * アカウント情報。
 * セーブデータ（ログイン情報など）を保持する。
 */

package com.example.app.domain;

import lombok.Data;

@Data
public class Account {
  private String id; // アカウントID
  private String loginToken; // ログイントークン
  private String passwordHash; // 別端末への引き継ぎ時に発行されるパスワード
  private int bgmVolume; // ゲーム内BGMの音量
  private int seVolume; // ゲーム内SEの音量

}
