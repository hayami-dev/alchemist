export const apiClient = {
  async post(url, data) {
    const response = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });

    if (!response.ok) {
      const errorBody = await response.json().catch(() => null);
      const message =
        errorBody?.message || `APIエラー: ${response.status} (${url})`;
      throw new Error(message);
    }

    return response.json();
  },

  async get(url) {
    const response = await fetch(url);

    if (!response.ok) {
      const errorBody = await response.json().catch(() => null);
      const message =
        errorBody?.message || `APIエラー: ${response.status} (${url})`;
      throw new Error(message);
    }

    return response.json();
  },
};
