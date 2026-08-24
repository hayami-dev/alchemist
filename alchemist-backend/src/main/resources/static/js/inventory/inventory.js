/**
 * バッグ（インベントリ）画面の操作に関するメソッド群。
 */

document.querySelectorAll(".js-tab-button").forEach((button) => {
  button.addEventListener("click", (event) => {
    const target = event.currentTarget.dataset.tabTarget;

    // 全パネルを隠す
    document.querySelectorAll(".js-tab-panel").forEach((panel) => {
      panel.classList.add("hidden");
    });

    // 選択されたパネルだけを表示
    document.getElementById(target).classList.remove("hidden");
  });
});
