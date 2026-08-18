/**
 * 調合機能に関するDBとの連携メソッド群
 */
import { apiClient } from "./apiClient.js";

export const AlchemyService = {
  craft(materialIds, calcTypes) {
    // 配列でmaterialIdとcalcTypeを渡す
    // TODO:バリデーション
    return apiClient.post("/alchemy/craft", { materialIds, calcTypes });
  },
};
