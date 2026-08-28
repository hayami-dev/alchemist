/**
 * 調合に関するmapper。
 */

package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlchemyMapper {

  @Select("SELECT item_id FROM craft_results WHERE calculated_value = #{calculatedValue}")
  public Long getCraftItemId(int calculatedValue);
}
