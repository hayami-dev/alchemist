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

  /* TODO:以下設計書と付け合せすること */
  // カバンにアイテムを追加。新規発見ならcatalogService.addCatalogも自動で叩く。
  // TODO: これってService…？Controllerでは……
  public void addItem(Long playerId, Long itemId, int qty) {
    InventoryItem existing = inventoryMapper.findByPlayerAndItem(playerId, itemId);
    if (existing != null) {
      inventoryMapper.updateQuantity(playerId, itemId, existing.getQuantity() + qty);
    } else {
      inventoryMapper.insertInventoryItem(playerId, itemId, qty);
    }
  }

  // 調合、売却、捨てるなどでアイテムを消費する。
  // 数量が0以下になれば自動でdeleteItemを呼ぶ。
  // アイテムを消費する。数量が0以下になったら削除する。
  public void consumeItem(Long playerId, Long itemId, int qty) {
    InventoryItem existing = inventoryMapper.findByPlayerAndItem(playerId, itemId);
    if (existing == null) {
      throw new IllegalStateException("消費対象のアイテムがインベントリにありません。itemId=" + itemId);
    }
    int remaining = existing.getQuantity() - qty;
    if (remaining <= 0) {
      inventoryMapper.deleteInventoryItem(playerId, itemId);
    } else {
      inventoryMapper.updateQuantity(playerId, itemId, remaining);
    }
  }

  // インベントリからアイテムを削除する。
  void deleteItem(Long playerId, Long itemId, int qty) {

  }

  // ツールアイテムを使用し、マテリアルアイテムを元に、別のアイテムを取得する。
  public Item useToolItem(Long playerId, Item materialItem, Item toolItem) {
    return null;
  }
}
