/**
 * 調合機能に関するDBとの連携メソッド群
 */
import { apiClient } from "./apiClient.js";

export const AlchemyService = {
  async craft(materialIds, calcTypes) {
    // TODO:バリデーション
    // 受け取った配列2つをJSONファイルにしてAPIへ送る
    return await apiClient.post("/alchemy/craft", {
      materialIds,
      calcTypes,
    });
  },
};
