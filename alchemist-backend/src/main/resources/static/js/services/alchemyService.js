/**
 * 調合機能に関するDBとの連携メソッド群
 */
import { apiClient } from "./apiClient";

const AlchemyService = {
  craft(materialIds, calcTypes) {
    return apiClient.post("/api/alchemy/craft", { materialIds, calcTypes });
  },
};
