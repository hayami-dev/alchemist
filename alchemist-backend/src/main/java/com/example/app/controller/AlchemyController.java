/**
 * 調合画面のコントローラー。
 * 調合画面、調合完了画面の表示、および調合時の処理を定義する。
 */

package com.example.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.controller.dto.CraftRequest;
import com.example.app.domain.Item;
import com.example.app.domain.enums.CalculationType;
import com.example.app.service.AlchemyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    model.addAttribute("allCalcTypes", CalculationType.values());
    model.addAttribute("defaultCalcType", CalculationType.ADD);
    return VIEW_PREFIX + "alchemy";
  }

  // 調合完了画面を表示する。調合結果のアイテムを表示する。
  // TODO:設計書を修正
  @GetMapping("/complete")
  public String showAlchemyComplete() {
    // ここではURLベタ打ち対応を行わない
    return VIEW_PREFIX + "complete";
  }

  // 結果を完了画面に渡し、調合したアイテムをインベントリに追加、マテリアルアイテムを減らす処理を行う。
  // また、レシピ情報を保存する。
  // TODO: 設計書、UML図の引数の修正
  @PostMapping("/craft")
  public ResponseEntity<?> craftItem(
      // RequestBodyで送られた配列データをListで受け取る
      @RequestBody CraftRequest request,
      HttpSession httpSession) {
    Item result = alchemyService.craftItem(request, DEBUG_PLAYER_ID);

    // TODO: recipeの登録

    return ResponseEntity.ok(result);
  }

  // material-pickerを返すエンドポイント
  @GetMapping("/material-picker")
  public String materialPicker(Model model) {
    // インベントリを取得
    model.addAttribute("items", alchemyService.getInventory(DEBUG_PLAYER_ID));
    return "fragments/alchemy/material-picker :: materialPicker";
  }

  // calc-pickerを返すエンドポイント
  // TODO: 設計書に追加
  @GetMapping("/calc-picker")
  public String calcPicker(Model model) {
    // 計算方法一覧を取得
    List<CalculationType> unlockedCalcTypes = alchemyService.getUnlockedCalcTypes(DEBUG_PLAYER_ID);
    model.addAttribute("unlockedCalcTypes", unlockedCalcTypes);
    return "fragments/alchemy/calc-picker :: calcPicker";
  }

}
