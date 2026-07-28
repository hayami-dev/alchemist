/**
 * 調合画面の操作に関するメソッド群。
 */

import { DialogService } from "./services/dialogService.js";

// material-pickerの表示/非表示切替
document.addEventListener("DOMContentLoaded", () => {
  const openButton = document.getElementById("openMaterialPickerButton");

  openButton.addEventListener("click", () => {
    DialogService.openWithContent("/alchemy/material-picker");
  });
});

// material-slotへ素材の情報を渡す
