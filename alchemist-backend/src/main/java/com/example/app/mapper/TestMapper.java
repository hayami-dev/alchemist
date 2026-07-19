package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.TestDomain;

@Mapper
public interface TestMapper {

  @Select("SELECT * FROM sample;")
  List<TestDomain> findAll();
}
