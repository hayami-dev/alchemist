/**
 * 調合画面の操作に関するメソッド群。
 */

import { DialogService } from "./services/dialogService.js";
import { AlchemyService } from "./services/alchemyService.js";

// material-slotへ素材の情報を渡す
// スロット番号 → 選択されたアイテムIDのオブジェクト
const selectedMaterials = {
  1: null,
  2: null,
  3: null,
};

// 今どのスロットのためにピッカーを開いているか
let currentTargetSlot = null;

// material-pickerの表示/非表示切替
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".js-material-slot-button").forEach((button) => {
    button.addEventListener("click", (event) => {
      currentTargetSlot = event.target.dataset.slotNum;
      DialogService.openWithContent("/alchemy/material-picker");
    });
  });
});

// 「調合する」ボタンの取得
const craftButton = document.getElementById("craftButton");

// 調合結果をJavaへ渡す
if (craftButton) {
  craftButton.addEventListener("click", async (event) => {
    event.preventDefault();

    const materialIds = Object.values(selectedMaterials)
      .filter((material) => material !== null)
      .map((material) => material.id);

    if (materialIds.length < 2) {
      alert("素材を2つ以上選んでください");
      return;
    }

    try {
      const result = await AlchemyService.craft(materialIds);
      console.log("調合結果", result);
    } catch (error) {
      console.error(error);
    }
  });
}

// ページ読み込み後、material-pickerの中身をfetchで差し替える用意
const bindMaterialPickerEvents = () => {
  document.querySelectorAll(".js-material-picker-item").forEach((button) => {
    button.addEventListener("click", (event) => {
      const itemId = event.currentTarget.dataset.itemId;
      const itemName = event.currentTarget.dataset.itemName;

      selectedMaterials[currentTargetSlot] = { id: itemId, name: itemName };

      console.log("hoge");

      renderMaterialSlots();
      DialogService.close();
    });
  });
};

// 状態(selectMaterials)を元に、各スロットの見た目を書き換える関数
const renderMaterialSlots = () => {
  for (const slotNum in selectedMaterials) {
    const slotButton = document.querySelector(`[data-slot-num="${slotNum}"]`);
    const material = selectedMaterials[slotNum];
    slotButton.textContent = material ? material.name : `素材${slotNum}`;
  }
};

// 最後にピッカーの中身を差し替える
window.onDialogContentLoaded = bindMaterialPickerEvents;
