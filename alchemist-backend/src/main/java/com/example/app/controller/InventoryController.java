package com.example.app.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.InventoryItem;
import com.example.app.domain.Item;
import com.example.app.domain.enums.ItemType;
import com.example.app.mapper.ItemMapper;
import com.example.app.service.InventoryService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;
  private final ItemMapper itemMapper;

  private static final String VIEW_PREFIX = "inventory/";
  private static final Long DEBUG_PLAYER_ID = 1L;

  // プレイヤーのカバン（インベントリ）画面を表示する。
  // 同アイテムは×表示でまとめる。レア度順に並び替える。
  @GetMapping
  public String showInventory(
      HttpSession session,
      Long playerId,
      Model model) {
    List<InventoryItem> items = inventoryService.getInventoryItems(DEBUG_PLAYER_ID);
    // 条件（TOOL以外かどうか）で true / false の2つに分割する
    Map<Boolean, List<InventoryItem>> partitioned = items.stream()
        .collect(Collectors.partitioningBy(
            item -> item.getItem().getItemType() != ItemType.TOOL));

    // true: 条件に当てはまるもの (TOOL 以外 = 素材など)
    List<InventoryItem> materialItems = partitioned.get(true);

    // false: 条件に当てはまらないもの (TOOL)
    List<InventoryItem> toolItems = partitioned.get(false);

    // TODO:所持数を出すにはInventoryItemクラスに入れる必要あり

    model.addAttribute("materialItems", materialItems);
    model.addAttribute("toolItems", toolItems);
    return VIEW_PREFIX + "inventory";
  }

  // アイテムメニューを表示するエンドポイント
  // TODO:設計書反映
  @GetMapping("/item-menu")
  public String itemMenu(@RequestParam Long itemId, Model model) {
    Item item = itemMapper.findById(itemId);
    // item-nameへ渡す値を揃えるためInventoryItemでラップ
    InventoryItem res = new InventoryItem();
    res.setItem(item);
    model.addAttribute("item", res);

    if (item.getItemType() == ItemType.TOOL) {
      model.addAttribute("isTool", true);
    }
    return "fragments/inventory/item-menu :: itemMenu";
  }

}
