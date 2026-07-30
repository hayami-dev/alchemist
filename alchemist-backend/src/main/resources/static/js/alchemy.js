/**
 * 調合画面の操作に関するメソッド群。
 */

import { DialogService } from "./services/dialogService.js";

// material-pickerの表示/非表示切替
document.querySelectorAll(".js-material-slot-button").forEach((button) => {
  button.addEventListener("click", (event) => {
    const slotNum = event.target.dataset.slotNum;
    console.log("slotNum", slotNum);

    DialogService.openWithContent("/alchemy/material-picker");
  });
});

// material-slotへ素材の情報を渡す
