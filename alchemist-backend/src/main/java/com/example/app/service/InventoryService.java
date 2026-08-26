package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.InventoryItem;
import com.example.app.domain.Item;
import com.example.app.mapper.InventoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryMapper inventoryMapper;

  // インベントリの全アイテムを取得する。
  public List<InventoryItem> getInventoryItems(Long playerId) {
    return inventoryMapper.findAll(playerId);
  }

  // 入ってきたアイテムが表示できる正しいアイテムかを判定する。
  public boolean isValidItem(Long itemId) {
    return false;
  }

  // カバンの最大容量（上限数）を超えて満タンかどうかを判定する。
  // TODO: ほんとに引数itemId？
  public boolean isBagFull(Long itemId) {
    return false;
  }

  // カバンにアイテムを追加。新規発見ならcatalogService.addCatalogも自動で叩く。
  // TODO: これってService…？Controllerでは……
  void addItem(Long playerId, Long itemId, int qty) {
  }

  // 調合、売却、捨てるなどでアイテムを消費する。
  // 数量が0以下になれば自動でdeleteItemを呼ぶ。
  void consumeItem(Long playerId, Long itemId, int qty) {

  }

  // インベントリからアイテムを削除する。
  void deleteItem(Long playerId, Long itemId, int qty) {

  }

  // ツールアイテムを使用し、マテリアルアイテムを元に、別のアイテムを取得する。
  public Item useToolItem(Long playerId, Item materialItem, Item toolItem) {
    return null;
  }
}
