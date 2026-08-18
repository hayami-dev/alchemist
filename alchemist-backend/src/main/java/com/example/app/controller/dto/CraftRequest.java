/**
 * フロントから渡された調合素材および方法を受け取るDTO。
 * 素材(Long)と調合方法(文字列)のListを保持する。
 * TODO:設計書に加筆
 */

package com.example.app.controller.dto;

import java.util.List;

import com.example.app.domain.enums.CalculationType;

import lombok.Data;

@Data
public class CraftRequest {
  private List<Long> materialIds;
  private List<CalculationType> calcTypes;
}
