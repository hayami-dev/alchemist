/**
 * 調合機能に関するDBとの連携メソッド群
 */
import { apiClient } from "./apiClient.js";

export const AlchemyService = {
  craft(materialIds, calcTypes) {
    return apiClient.post("/api/alchemy/craft", { materialIds, calcTypes });
  },
};
