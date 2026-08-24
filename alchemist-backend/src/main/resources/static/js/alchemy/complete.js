/**
 * 調合完了画面の操作に関するメソッド群。
 */

document.addEventListener("DOMContentLoaded", () => {
  const raw = sessionStorage.getItem("craftResult");

  // 直接URLを叩かれた等、結果が無い場合のガード
  if (!raw) {
    window.location.href = "/alchemy";
    return;
  }

  // htmlへ調合結果テキストを渡す
  // TODO:もしrawはあるが中身がNULLだった場合の表示分け
  const result = JSON.parse(raw);
  document.getElementById("resultItemName").textContent = result.name;
  document.getElementById("resultItemDescription").textContent =
    result.description !== null ? result.description : "NULL";

  // 表示後、セッションストレージから調合結果を削除
  sessionStorage.removeItem("craftResult");
});
