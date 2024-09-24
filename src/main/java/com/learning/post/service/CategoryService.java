package com.learning.post.service;

import com.learning.post.dto.CategoryDto;

import java.util.List;


public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    public CategoryDto getCategoryById(Long categoryId);

    public List<CategoryDto> getAllCategories();

    public String deleteCategory(Long categoryId);


}
