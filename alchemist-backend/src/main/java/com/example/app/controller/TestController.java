package com.example.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.TestDomain;
import com.example.app.mapper.TestMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final TestMapper testMapper;

  @GetMapping("/")
  public List<TestDomain> getTestData() {

    List<TestDomain> testList = testMapper.findAll();
    return testList;
  }

}
