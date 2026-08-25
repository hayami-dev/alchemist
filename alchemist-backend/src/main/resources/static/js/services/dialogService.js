export const DialogService = {
  async openWithContent(url, options = {}) {
    const {
      onPositive = null,
      positiveText = "OK",
      negativeText = "キャンセル",
    } = options;

    const response = await fetch(url);
    if (!response.ok) {
      console.error(`サーバーエラーが発生しました Status: ${response.status}`);
      const errorHtml = await response.text();
      console.error("エラーログ詳細(HTML):", errorHtml);
      return;
    }

    const html = await response.text();

    document.getElementById("dialogContent").innerHTML = html;
    document.getElementById("dialogShell").classList.remove("hidden");
    document.getElementById("dialogShell").classList.add("flex");

    // ネガティブボタンのテキストを変更
    const negativeButton = document.getElementById("dialogNegativeButton");
    if (negativeButton) {
      negativeButton.textContent = negativeText;
    }

    // ポジティブボタン
    const positiveButton = document.getElementById("dialogPositiveButton");
    if (positiveButton) {
      // 過去に設定されたイベントリスナーをリセットするため、クローンして置き換える
      const newPositiveButton = positiveButton.cloneNode(true);
      positiveButton.parentNode.replaceChild(newPositiveButton, positiveButton);

      if (onPositive) {
        // ボタンを表示
        newPositiveButton.style.display = "";
        // ボタンテキストの変更
        newPositiveButton.textContent = positiveText;
        // onPositiveとして渡された関数を実行
        newPositiveButton.addEventListener("click", async () => {
          await onPositive();
        });
      } else {
        // ポジティブボタン不要時はボタンを非表示
        newPositiveButton.style.display = "none";
      }
    }

    if (window.onDialogContentLoaded) {
      window.onDialogContentLoaded();
    }
  },
  close() {
    document.getElementById("dialogShell").classList.add("hidden");
    document.getElementById("dialogShell").classList.remove("flex");
  },
};

// このファイルが読み込まれた時点でネガティブボタンにイベントを仕込んでおく
// 合わせてボタンの初期化を行う
document.addEventListener("DOMContentLoaded", () => {
  const closeButton = document.getElementById("dialogNegativeButton");
  if (closeButton) {
    closeButton.addEventListener("click", () => {
      DialogService.close();
    });
  }
});
