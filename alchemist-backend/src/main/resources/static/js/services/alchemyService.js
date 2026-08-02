/**
 * 調合機能に関するDBとの連携メソッド群
 */
const AlchemyService = {
  async craft(materialIds, methodTypes) {
    const response = await fetch("api/alchemy/craft", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ materialIds, methodTypes }),
    });

    if (!response.ok) {
      throw new Error(`調合APIエラー:${response.status}`);
    }

    return response.json();
  },
};
