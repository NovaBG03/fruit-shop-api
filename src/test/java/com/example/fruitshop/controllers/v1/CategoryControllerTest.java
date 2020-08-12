package com.example.fruitshop.controllers.v1;

import com.example.fruitshop.api.v1.model.CategoryDTO;
import com.example.fruitshop.controllers.RestResponseEntityExceptionHandler;
import com.example.fruitshop.exceptions.ResourceNotFoundException;
import com.example.fruitshop.services.CategoryService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.example.fruitshop.controllers.v1.CategoryController.BASE_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllCategoriesTest() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        CategoryDTO categoryDTO2 = new CategoryDTO();

        List<CategoryDTO> categories = Arrays.asList(categoryDTO1, categoryDTO2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(categories.size())));
    }

    @Test
    void getCategoryByNameTest() throws Exception {
        final long id = 30_000_000_000L;
        final String name = "name";

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        categoryDTO.setName(name);

        when(categoryService.getCategoryByName(name)).thenReturn(categoryDTO);

        mvc.perform(get(BASE_URL + "/" + name)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andExpect(jsonPath("$.name", equalTo(name)));
    }

    @Test
    void getCategoryByNameNotFoundTest() throws Exception {

        when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get(BASE_URL + "/name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}