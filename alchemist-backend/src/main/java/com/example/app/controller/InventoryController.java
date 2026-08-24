package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

  private static final String VIEW_PREFIX = "inventory/";

  // プレイヤーのカバン（インベントリ）画面を表示する。
  // 同アイテムは×表示でまとめる。レア度順に並び替える。
  @GetMapping
  public String showInventory(
      HttpSession session,
      Long playerId,
      Model model) {
    return VIEW_PREFIX + "inventory";
  }
}
