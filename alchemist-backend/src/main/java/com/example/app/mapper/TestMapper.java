package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.Account;
import com.example.app.domain.Catalog;
import com.example.app.domain.Diary;
import com.example.app.domain.Item;
import com.example.app.domain.MaterialItem;
import com.example.app.domain.Player;
import com.example.app.domain.Recipe;
import com.example.app.domain.ToolItem;

@Mapper
public interface TestMapper {

  @Select("SELECT * FROM accounts")
  List<Account> findAllAccounts();

  @Select("SELECT * FROM players")
  List<Player> findAllPlayers();

  @Select("SELECT * FROM items")
  List<Item> findAllItems();

  @Select("SELECT * FROM items WHERE itemType ='MATERIAL'")
  List<MaterialItem> findAllMaterialItems();

  @Select("SELECT * FROM items WHERE itemType ='TOOL'")
  List<ToolItem> findAllToolItems();

  @Select("SELECT * FROM recipes")
  List<Recipe> findAllRecipes();

  @Select("SELECT * FROM diaries")
  List<Diary> findAllDiaries();

  @Select("SELECT * FROM catalogs")
  List<Catalog> findAllCatalogs();
}
