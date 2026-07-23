package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.InventoryItem;

@Mapper
public interface InventoryMapper {

  // 指定プレイヤーのカバン内アイテムを全件取得する。
  public List<InventoryItem> findAll(Long playerId);

}
