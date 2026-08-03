export const DialogService = {
  async openWithContent(url) {
    const response = await fetch(url);
    const html = await response.text();

    document.getElementById("dialogContent").innerHTML = html;
    document.getElementById("dialogShell").classList.remove("hidden");
    document.getElementById("dialogShell").classList.add("flex");

    if (window.onDialogContentLoaded) {
      window.onDialogContentLoaded();
    }
  },
  close() {
    document.getElementById("dialogShell").classList.add("hidden");
    document.getElementById("dialogShell").classList.remove("flex");
  },
};

// このファイルが読み込まれた時点で、閉じるボタンにイベントを仕込んでおく
document.addEventListener("DOMContentLoaded", () => {
  const closeButton = document.getElementById("dialogCloseButton");
  closeButton.addEventListener("click", () => {
    DialogService.close();
  });
});
