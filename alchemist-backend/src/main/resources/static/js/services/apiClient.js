export const apiClient = {
  async post(url, data) {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    if (!response.ok) {
      throw new Error(`APIエラー: ${response.status} (${url})`);
    }

    return response.json();
  },

  async get(url) {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error(`APIエラー: ${response.status} (${url})`);
    }

    return response.json();
  },
};
