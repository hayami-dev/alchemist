package com.example.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.app.domain.Catalog;
import com.example.app.domain.CatalogSlot;
import com.example.app.domain.Item;
import com.example.app.mapper.CatalogMapper;
import com.example.app.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {
  private final CatalogMapper catalogMapper;
  private final ItemMapper itemMapper;
  private final AlchemyService alchemyService;

  // プレイヤーのカタログ一覧を取得する。
  public List<CatalogSlot> getPlayerCatalog(Long playerId) {
    // itemIdとitemTypeを取得
    List<Item> allItems = catalogMapper.findAllItems();

    // playerIdからcatalogsを取得
    Set<Long> catalogedItemIds = catalogMapper.findCatalogedItemIds(playerId);

    return allItems.stream()
        .map(item -> new CatalogSlot(item, catalogedItemIds.contains(item.getId())))
        .toList();
  }

  // カタログの解放率を計算して返す。
  // TODO:設計書に追記
  public int getProgressRate(List<CatalogSlot> slots) {
    if (slots == null || slots.isEmpty()) {
      return 0;
    }

    // 解禁済み（isCataloged == true）の件数をカウント[cite: 3]
    long catalogedCount = slots.stream()
        .filter(slot -> slot != null && slot.isCataloged()) // null チェックも含められる
        .count();

    // 四捨五入して int 型で返却（Math.round を使用）
    double rate = ((double) catalogedCount / slots.size()) * 100;
    return (int) Math.round(rate);
  }
}
