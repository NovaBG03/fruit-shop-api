package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.CategoryMapper;
import com.example.fruitshop.api.v1.model.CategoryDTO;
import com.example.fruitshop.exceptions.ResourceNotFoundException;
import com.example.fruitshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::categoryToCategoryDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
