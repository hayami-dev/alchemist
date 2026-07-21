package com.example.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Account;
import com.example.app.domain.Catalog;
import com.example.app.domain.Diary;
import com.example.app.domain.Item;
import com.example.app.domain.MaterialItem;
import com.example.app.domain.Player;
import com.example.app.domain.Recipe;
import com.example.app.domain.ToolItem;
import com.example.app.mapper.TestMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final TestMapper testMapper;

  @GetMapping("/accounts")
  public List<Account> getAccounts() {

    List<Account> testList = testMapper.findAllAccounts();
    return testList;
  }

  @GetMapping("/players")
  public List<Player> getPlayers() {

    List<Player> testList = testMapper.findAllPlayers();
    return testList;
  }

  @GetMapping("/items")
  public List<Item> getItems() {

    List<Item> testList = testMapper.findAllItems();
    return testList;
  }

  @GetMapping("/material-items")
  public List<MaterialItem> getMaterialItems() {

    List<MaterialItem> testList = testMapper.findAllMaterialItems();
    return testList;
  }

  @GetMapping("/tool-items")
  public List<ToolItem> getToolItems() {

    List<ToolItem> testList = testMapper.findAllToolItems();
    return testList;
  }

  @GetMapping("/recipes")
  public List<Recipe> getToolRecipes() {

    List<Recipe> testList = testMapper.findAllRecipes();
    return testList;
  }

  @GetMapping("/diaries")
  public List<Diary> getDiaries() {

    List<Diary> testList = testMapper.findAllDiaries();
    return testList;
  }

  @GetMapping("/catalogs")
  public List<Catalog> getCatalogs() {

    List<Catalog> testList = testMapper.findAllCatalogs();
    return testList;
  }

}
