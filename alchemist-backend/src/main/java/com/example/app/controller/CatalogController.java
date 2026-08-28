package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.CatalogSlot;
import com.example.app.service.CatalogService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

  private final CatalogService catalogService;

  private static final String VIEW_PREFIX = "catalog/";
  private static final Long DEBUG_PLAYER_ID = 1L;

  @GetMapping
  public String showCatalog(HttpSession session, Long playerId, Model model) {

    // レシピ画面に枠と中身を表示する
    List<CatalogSlot> slots = catalogService.getPlayerCatalog(DEBUG_PLAYER_ID);

    // TODO:playersテーブルからレシピ解放達成率を取得する
    // →今はService から直接計算を行い表示する
    int completionRate = catalogService.getProgressRate(slots);

    model.addAttribute("slots", slots);
    model.addAttribute("completionRate", completionRate);

    return VIEW_PREFIX + "catalog";
  }
}
