package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.Item;

@Mapper
public interface AlchemyMapper {

  // 渡された数値（id）から対応したマテリアルアイテムを1件取得する。
  @Select("SELECT * FROM items WHERE id = #{itemId}")
  public Item findById(Long itemId);

}
