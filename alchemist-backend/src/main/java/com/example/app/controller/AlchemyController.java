/**
 * 調合画面のコントローラー。
 * 調合画面、調合完了画面の表示、および調合時の処理を定義する。
 */

package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Item;
import com.example.app.service.AlchemyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/alchemy")
@RequiredArgsConstructor
public class AlchemyController {

  private final AlchemyService alchemyService;

  private static final String VIEW_PREFIX = "alchemy/";
  private static final Long DEBUG_PLAYER_ID = 1L;

  // 調合画面を表示する。
  // インベントリを表示してマテリアルアイテムを選択できるようにする。
  @GetMapping
  public String showAlchemy(
      HttpSession session,
      Model model) {
    String message = "hoge";
    model.addAttribute("message", message);
    return VIEW_PREFIX + "alchemy";
  }

  // 調合完了画面を表示する。調合結果のアイテムを表示する。
  @GetMapping("/complete")
  public String showAlchemyComplete(Model model) {
    return null;
  }

  // 結果を完了画面に渡し、調合したアイテムをインベントリに追加、マテリアルアイテムを減らす処理を行う。
  // また、レシピ情報を保存する。
  @PostMapping("/craft")
  public String craftItem(
      RedirectAttributes ra,
      HttpSession session,
      Long playerId,
      Item item,
      Long recipeId) {

    return null;
  }

  // material-pickerを返すエンドポイント
  @GetMapping("/material-picker")
  public String materialPicker(Model model) {
    // インベントリを取得
    model.addAttribute("items", alchemyService.getInventory(DEBUG_PLAYER_ID));
    return "fragments/alchemy/material-picker :: materialPicker";
  }

}
