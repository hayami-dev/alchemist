/**
 * バッグ（インベントリ）画面の操作に関するメソッド群。
 */

import { DialogService } from "../services/dialogService.js";

document.querySelectorAll(".js-tab-button").forEach((button) => {
  button.addEventListener("click", (event) => {
    const currentButton = event.currentTarget;
    const target = currentButton.dataset.tabTarget;

    // 1. 全タブボタンから is-active を削除し、クリックされたボタンだけに付与
    document.querySelectorAll(".js-tab-button").forEach((btn) => {
      btn.classList.remove("is-active");
    });
    currentButton.classList.add("is-active");

    // 2. 全パネルを隠す
    document.querySelectorAll(".js-tab-panel").forEach((panel) => {
      panel.classList.add("hidden");
    });

    // 3. 選択されたパネルだけを表示
    const targetPanel = document.getElementById(target);
    if (targetPanel) {
      targetPanel.classList.remove("hidden");
    }
  });
});

document.addEventListener("DOMContentLoaded", () => {
  // 初期表示時：非非表示(hiddenでない)のパネルに対応するタブボタンに is-active を付与
  const visiblePanel = document.querySelector(".js-tab-panel:not(.hidden)");
  if (visiblePanel) {
    const activeTab = document.querySelector(
      `.js-tab-button[data-tab-target="${visiblePanel.id}"]`,
    );
    if (activeTab) {
      activeTab.classList.add("is-active");
    }
  }

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
