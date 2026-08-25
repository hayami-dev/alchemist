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

    List<CatalogSlot> slots = catalogService.getPlayerCatalog(DEBUG_PLAYER_ID);
    model.addAttribute("slots", slots);
    return VIEW_PREFIX + "catalog";
  }
}
