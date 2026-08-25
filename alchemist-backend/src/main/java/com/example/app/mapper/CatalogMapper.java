package com.example.app.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.Catalog;
import com.example.app.domain.Item;
import com.example.app.domain.Recipe;

@Mapper
public interface CatalogMapper {

  // プレイヤーがこれまでに発見して図鑑解禁済みのアイテムid一覧を取得する。
  @Select("SELECT item_id FROM catalogs WHERE player_id = #{playerId}")
  public Set<Long> findCatalogedItemIds(Long playerId);

  // （枠、達成率表示のため）DBに存在するアイテムのリストを取得する。
  @Select("SELECT id, itemType FROM items")
  public List<Item> findAllItems();

  // 新規にアイテムを発見した際、カタログにINSERT登録する。
  public void insertCatalog(Long playerId, Catalog catalog);

  // 新たにアイテムの調合式（レシピ）を解禁した際、カタログにUPDATE変更する。
  public void updateRecipeData(Long playerId, Long catalogId, Recipe recipe);

  // カタログアイテム毎のメモを変更する。
  public void updateMemo(Long playerId, Long catalogId);
}
