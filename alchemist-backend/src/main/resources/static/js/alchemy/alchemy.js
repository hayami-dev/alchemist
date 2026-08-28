/**
 * 調合画面の操作に関するメソッド群。
 */

import { DialogService } from "../services/dialogService.js";
import { AlchemyService } from "../services/alchemyService.js";

// material-slotへ素材の情報を渡す
// スロット番号 → 選択されたアイテムIDのオブジェクト
const selectedMaterials = {
  1: null,
  2: null,
  3: null,
};

// calc-slotへ調合方法の情報を渡す
// 初期値は足し算
// TODO: 直じゃなくてenumをもってくる？
const selectedCalcs = {
  1: "ADD",
  2: "ADD",
};

// 今どのスロットのためにピッカーを開いているか
let currentTargetMaterialSlot = null;
let currentTargetCalcSlot = null;

// material-pickerの表示/非表示切替
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".js-material-slot-button").forEach((button) => {
    button.addEventListener("click", (event) => {
      currentTargetMaterialSlot = event.target.dataset.slotNum;
      DialogService.openWithContent("/alchemy/material-picker");
    });
  });
});

// calc-pickerの表示/非表示切り替え
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".js-calc-slot-button").forEach((button) => {
    button.addEventListener("click", (event) => {
      currentTargetCalcSlot = event.currentTarget.dataset.slotNum;
      DialogService.openWithContent("/alchemy/calc-picker");
    });
  });
});

// 「調合する」ボタンの取得
const craftButton = document.querySelector(".js-craft-button");

// 調合結果をJavaへ渡す
if (craftButton) {
  craftButton.addEventListener("click", async (event) => {
    event.preventDefault();

    // 選択中の素材オブジェクトを取得
    const activeMaterials = Object.values(selectedMaterials).filter(
      (material) => material !== null,
    );

    if (activeMaterials.length < 2) {
      alert("素材を2つ以上選んでください");
      return;
    }

    // ここで確認ダイアログを出す
    await DialogService.openWithContent("/alchemy/confirm-modal", {
      positiveText: "調合する",
      negativeText: "やめる",
      onPositive: async () => {
        DialogService.close();

        // 調合・送信処理 materialId,calcTypeの配列(文字列)を作成
        const materialIds = activeMaterials.map((m) => m.id);
        const requiredCalcCount = materialIds.length - 1;
        const calcTypes = Object.values(selectedCalcs).slice(
          0,
          requiredCalcCount,
        );
        // alchemyService.jsへ渡す
        try {
          const result = await AlchemyService.craft(materialIds, calcTypes);

          sessionStorage.setItem("craftResult", JSON.stringify(result));
          window.location.href = "/alchemy/complete";
        } catch (error) {
          console.error(error);
          DialogService.showMessage(error.message || "調合に失敗しました");
        }
      },
    });

    // 確認ダイアログへ素材の情報を渡す
    const listElement = document.getElementById("confirmMaterialList");
    if (listElement) {
      // 素材を集計
      const summarized = summarizeMaterials(activeMaterials);
      listElement.innerHTML = summarized
        .map((m) => `<li>${m.name}<span>×${m.count}</span></li>`)
        .join("");
    }
  });
}

// 表示のため、確認ダイアログに表示する素材を集計する
const summarizeMaterials = (materials) => {
  const summary = {};
  for (const material of materials) {
    if (summary[material.name]) {
      summary[material.name].count += 1;
    } else {
      summary[material.name] = { name: material.name, count: 1 };
    }
  }
  return Object.values(summary);
};

// ダイアログ読み込み後、material-pickerの中身をfetchで差し替える用意
const bindMaterialPickerEvents = () => {
  document.querySelectorAll(".js-material-picker-item").forEach((button) => {
    button.addEventListener("click", (event) => {
      const itemId = event.currentTarget.dataset.itemId;
      const itemName = event.currentTarget.dataset.itemName;

      selectedMaterials[currentTargetMaterialSlot] = {
        id: itemId,
        name: itemName,
      };

      renderMaterialSlots();
      DialogService.close();
    });
  });
};

// 状態(selectedMaterials)を元に、各スロットの見た目を書き換える関数
const renderMaterialSlots = () => {
  for (const slotNum in selectedMaterials) {
    const slotButton = document.querySelector(
      `.js-material-slot-button[data-slot-num="${slotNum}"]`,
    );
    const material = selectedMaterials[slotNum];
    slotButton.textContent = material ? material.name : `素材${slotNum}`;
  }
};

// ダイアログ読み込み後、calc-pickerの中身をfetchで差し替える用意
const bindCalcPickerEvents = () => {
  document.querySelectorAll(".js-calc-picker-button").forEach((button) => {
    button.addEventListener("click", (event) => {
      const calcType = event.currentTarget.dataset.calcType;

      selectedCalcs[currentTargetCalcSlot] = calcType;

      renderCalcSlots();
      DialogService.close();
    });
  });
};

// 状態(selectedCalcs)を元に、各スロットの見た目を書き換える関数
const renderCalcSlots = () => {
  for (const slotNum in selectedCalcs) {
    const slotButton = document.querySelector(
      `.js-calc-slot-button[data-slot-num="${slotNum}"]`,
    );
    console.log("slotButton:", slotButton);
    const calcType = selectedCalcs[slotNum];

    // スロット内の全アイコンを非表示
    const allIcons = slotButton.querySelectorAll(".js-calc-icon");
    allIcons.forEach((icon) => icon.classList.add("hidden"));

    // calcType に一致するアイコンだけを表示する
    if (calcType) {
      const activeIcon = slotButton.querySelector(
        `.js-calc-icon[data-calc-type="${calcType}"]`,
      );
      if (activeIcon) {
        activeIcon.classList.remove("hidden");
      }
    }
  }
};

// 最後にピッカーの中身を差し替える
// ダイアログの中身が差し込まれた後、何を初期化すべきかをまとめて判断する
const bindDialogContentEvents = () => {
  bindMaterialPickerEvents();
  bindCalcPickerEvents();
};

window.onDialogContentLoaded = bindDialogContentEvents;

// material-slot3の素材をクリアするボタンの処理
document.addEventListener("DOMContentLoaded", () => {
  const clearMaterialsButton = document.getElementById(
    "js-clear-materials-button",
  );

  if (clearMaterialsButton) {
    clearMaterialsButton.addEventListener("click", () => {
      selectedMaterials[3] = null;
      renderMaterialSlots();
    });
  }
});
