/**
 * 調合画面の操作に関するメソッド群。
 */

import { DialogService } from "./services/dialogService.js";

// 今どのスロットのためにピッカーを開いているか
let currentTargetSlot = null;

// material-pickerの表示/非表示切替
document.querySelectorAll(".js-material-slot-button").forEach((button) => {
  button.addEventListener("click", (event) => {
    currentTargetSlot = event.target.dataset.slotNum;

    DialogService.openWithContent("/alchemy/material-picker");
  });
});

// material-slotへ素材の情報を渡す
// スロット番号 → 選択されたアイテムIDのオブジェクト
let selectedMaterials = {
  1: null,
  2: null,
  3: null,
};

// material-picker.htmlの中の各アイテム行から呼ばれる想定の関数
const selectMaterials = (itemId, itemName) => {
  selectMaterials[currentTargetSlot] = { id: itemId, name: itemName };
  renderMaterialSlots(); // 状態が変わったので、画面を更新する
  DialogService.close();
};

// 状態(selectMaterials)を元に、各スロットの見た目を書き換える関数
const renderMaterialSlots = () => {
  for (const slotNum in selectedMaterials) {
    const slotButton = document.querySelector(`[data-slot-num="${slotNum}"]`);
    const material = selectedMaterials[slotNum];
    slotButton.textContent = material ? material.name : `素材${slotNum}`;
  }
};

// 調合結果をJavaへ渡す
const craftButton = document.getElementById("craftButton");

craftButton.addEventListener("click", async (event) => {
  event.preventDefault();

  const materialIds = Object.values(selectMaterials)
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
