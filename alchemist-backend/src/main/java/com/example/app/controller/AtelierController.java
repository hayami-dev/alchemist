package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AtelierController {

  private static final String VIEW_PREFIX = "atelier/";

  @GetMapping
  public String showAtelier(
      HttpSession session, Long playerId, Model model) {
    return VIEW_PREFIX + "atelier";
  }
}
