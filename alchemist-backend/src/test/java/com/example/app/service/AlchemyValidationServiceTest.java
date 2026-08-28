package com.example.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

import com.example.app.domain.MaterialItem;
import com.example.app.domain.enums.ItemType;
import com.example.app.mapper.ItemMapper;
import com.example.app.domain.Item;
import com.example.app.domain.MaterialItem;
import com.example.app.domain.enums.ItemType;

class AlchemyValidationServiceTest {

  private final AlchemyValidationService service = new AlchemyValidationService(null);

  @Test
  void hasEnoughSelectedMaterials_2つ以上ならtrue() {
    AlchemyValidationService service = new AlchemyValidationService(null); // itemMapperは今回使わないのでnullでOK

    List<Long> materialIds = List.of(1L, 2L);

    assertTrue(service.hasEnoughSelectedMaterials(materialIds));
  }

  @Test
  void hasEnoughSelectedMaterials_1つだけならfalse() {
    AlchemyValidationService service = new AlchemyValidationService(null);

    List<Long> materialIds = List.of(1L);

    assertFalse(service.hasEnoughSelectedMaterials(materialIds));
  }

  @Test
  void isMaterialItem_MATERIALのみならtrue() {
    ItemMapper mockItemMapper = mock(ItemMapper.class); // 偽物のItemMapperを作る
    AlchemyValidationService service = new AlchemyValidationService(mockItemMapper);

    Item material = new MaterialItem();
    material.setItemType(ItemType.MATERIAL);

    assertTrue(service.isMaterialItem(List.of(material)));
  }

  @Test
  void 結果が素材のどれとも異なる場合はtrue() {
    Long resultItemId = 99L;
    List<Long> materialIds = List.of(1L, 2L, 3L);

    assertTrue(service.isDifferentResult(resultItemId, materialIds));
  }

  @Test
  void 結果が素材の1つと同じ場合はfalse() {
    Long resultItemId = 2L;
    List<Long> materialIds = List.of(1L, 2L, 3L);

    assertFalse(service.isDifferentResult(resultItemId, materialIds));
  }

  @Test
  void 結果が全ての素材と同じ場合はfalse() {
    // 例: 素材が全部同じアイテムで、掛け算(x1)などで結果も同じ値になったケース
    Long resultItemId = 5L;
    List<Long> materialIds = List.of(5L, 5L, 5L);

    assertFalse(service.isDifferentResult(resultItemId, materialIds));
  }

  @Test
  void 素材が1つで結果と異なる場合はtrue() {
    Long resultItemId = 10L;
    List<Long> materialIds = List.of(1L);

    assertTrue(service.isDifferentResult(resultItemId, materialIds));
  }

  @Test
  void 素材が1つで結果と同じ場合はfalse() {
    Long resultItemId = 1L;
    List<Long> materialIds = List.of(1L);

    assertFalse(service.isDifferentResult(resultItemId, materialIds));
  }
}