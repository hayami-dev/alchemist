package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

  /* TODO:以下設計書と付け合せすること */
  // 指定プレイヤー・アイテムのインベントリ1件を取得する（無ければnull）
  @Select("SELECT * FROM inventories WHERE player_id = #{playerId} AND item_id = #{itemId}")
  @Results({
      @Result(property = "quantity", column = "quantity"),
      @Result(property = "item", column = "item_id", one = @One(select = "com.example.app.mapper.ItemMapper.findById"))
  })
  InventoryItem findByPlayerAndItem(@Param("playerId") Long playerId, @Param("itemId") Long itemId);

  // 所持数量を、渡した値に上書きする
  @Update("UPDATE inventories SET quantity = #{quantity} WHERE player_id = #{playerId} AND item_id = #{itemId}")
  void updateQuantity(@Param("playerId") Long playerId, @Param("itemId") Long itemId, @Param("quantity") int quantity);

  // 新規にインベントリ行を追加する
  @Insert("INSERT INTO inventories (player_id, item_id, quantity) VALUES (#{playerId}, #{itemId}, #{quantity})")
  void insertInventoryItem(@Param("playerId") Long playerId, @Param("itemId") Long itemId,
      @Param("quantity") int quantity);

  // インベントリ行を削除する（数量が0になった時用）
  @Delete("DELETE FROM inventories WHERE player_id = #{playerId} AND item_id = #{itemId}")
  void deleteInventoryItem(@Param("playerId") Long playerId, @Param("itemId") Long itemId);
}
