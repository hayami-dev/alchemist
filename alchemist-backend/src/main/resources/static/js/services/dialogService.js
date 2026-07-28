export const DialogService = {
  async openWithContent(url) {
    const response = await fetch(url);
    const html = await response.text();

    document.getElementById("dialogContent").innerHTML = html;
    document.getElementById("dialogShell").classList.remove("hidden");
    document.getElementById("dialogShell").classList.add("flex");
  },
  close() {
    document.getElementById("dialogShell").classList.add("hidden");
  },
};
