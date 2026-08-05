package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.Item;

@Mapper
public interface ItemMapper {

  // 渡された数値（id）から対応したアイテムを1件取得する。
  Item findById(Long itemId);
}
