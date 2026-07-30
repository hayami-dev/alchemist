package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.InventoryItem;

@Mapper
public interface InventoryMapper {

  // 指定プレイヤーのカバン内アイテムを全件取得する。
  @Select("SELECT * FROM inventories WHERE player_id = #{playerId}")
  @Results({
      @Result(property = "quantity", column = "quantity"),
      @Result(property = "item", column = "item_id", one = @One(select = "com.example.app.mapper.ItemMapper.findById"))
  })
  public List<InventoryItem> findAll(Long playerId);

}
