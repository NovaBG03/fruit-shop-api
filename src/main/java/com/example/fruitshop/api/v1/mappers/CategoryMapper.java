package com.example.fruitshop.api.v1.mappers;

import com.example.fruitshop.api.v1.model.CategoryDTO;
import com.example.fruitshop.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
