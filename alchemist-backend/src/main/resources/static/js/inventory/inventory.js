/**
 * バッグ（インベントリ）画面の操作に関するメソッド群。
 */

import { DialogService } from "../services/dialogService.js";

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

document.addEventListener("DOMContentLoaded", () => {
  // item-listをクリック
  document.querySelectorAll(".js-item-list-button").forEach((button) => {
    button.addEventListener("click", (event) => {
      const itemId = event.currentTarget.dataset.itemId;
      DialogService.openWithContent(`/inventory/item-menu?itemId=${itemId}`);
    });
  });

  // item-menu内のボタンをクリック
  document.addEventListener("click", (event) => {
    const menuButton = event.target.closest(".js-item-menu-button");
    if (!menuButton) return;

    const action = menuButton.dataset.action; // use または discard

    if (action === "use") {
      // 使うの処理
      console.log("use");
    } else if (action === "discard") {
      console.log("discard");
    }
  });
});
