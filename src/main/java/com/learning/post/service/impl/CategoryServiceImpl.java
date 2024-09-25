package com.learning.post.service.impl;

import com.learning.post.dto.CategoryDto;
import com.learning.post.dto.PostDto;
import com.learning.post.entity.Category;
import com.learning.post.entity.Post;
import com.learning.post.exception.ResourceNotFoundException;
import com.learning.post.repository.CategoryRepo;
import com.learning.post.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category categoryToBeSaved = modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryRepo.save(categoryToBeSaved), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category categoryToUpdated = modelMapper.map(categoryDto, Category.class);
        categoryToUpdated.setId(id);
        return modelMapper.map(categoryRepo.save(categoryToUpdated), CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return modelMapper.map(categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("No Category", "id", categoryId)), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categories) {
            List<PostDto> postDtoList = new ArrayList<>();
            for (Post post : category.getPosts()) {
                PostDto postDto = modelMapper.map(post, PostDto.class);
                postDto.setLikesCount(Long.valueOf(post.getLikes().size()));
                postDtoList.add(postDto);
            }
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDto.setPostsDto(postDtoList);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
        return "Deleted Successfully";
    }
}
