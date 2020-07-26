package com.example.fruitshop.api.v1.mappers;

import com.example.fruitshop.api.v1.model.CategoryDTO;
import com.example.fruitshop.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTOTest() {
        final Long id = 1L;
        final String name = "name";

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(id, categoryDTO.getId());
        assertEquals(name, categoryDTO.getName());
    }
}