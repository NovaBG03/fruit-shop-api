package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.CategoryMapper;
import com.example.fruitshop.api.v1.model.CategoryDTO;
import com.example.fruitshop.domain.Category;
import com.example.fruitshop.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {
        List<Category> categoriesFromRepository = new ArrayList<>();

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("category1");
        categoriesFromRepository.add(category1);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("category2");
        categoriesFromRepository.add(category2);

        when(categoryRepository.findAll()).thenReturn(categoriesFromRepository);

        List<CategoryDTO> categories = categoryService.getAllCategories();

        assertEquals(categoriesFromRepository.size(), categories.size());
    }

    @Test
    void getCategoryByName() {
        final Long id = 1L;
        final String name = "name";

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        when(categoryRepository.findByName(name)).thenReturn(Optional.of(category));

        CategoryDTO categoryDTO = categoryService.getCategoryByName(name);

        assertNotNull(categoryDTO);
        assertEquals(id, categoryDTO.getId());
        assertEquals(name, categoryDTO.getName());
    }
}