package com.learning.post.service.impl;

import com.learning.post.dto.PostDto;
import com.learning.post.entity.Category;
import com.learning.post.entity.Post;
import com.learning.post.entity.User;
import com.learning.post.repository.CategoryRepo;
import com.learning.post.repository.PostRepo;
import com.learning.post.repository.UserRepo;
import com.learning.post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Long categoryId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = modelMapper.map(postDto, Post.class);
        User userToBeSaved = User.builder().id(user.getId()).build();
        Category category = categoryRepo.findById(categoryId).get();
        post.setCategory(category);
        post.setUser(userToBeSaved);
        post.setCreatedOn(LocalDate.now());
        post.setModifiedOn(LocalDate.now());
        return modelMapper.map(postRepo.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo.findById(postId).get();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setModifiedOn(LocalDate.now());
        return modelMapper.map(postRepo.save(post), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        return modelMapper.map(postRepo.findById(postId).get(), PostDto.class);
    }

    @Override
    public List<PostDto> getPostByUserId() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToBeSaved = User.builder().id(user.getId()).build();
        List<Post> posts = postRepo.findByUser(userToBeSaved);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : posts)
            postDtoList.add(modelMapper.map(post, PostDto.class));
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByCategoryId(Long categoryId) {
        Category category = Category.builder().id(categoryId).build();
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : posts)
            postDtoList.add(modelMapper.map(post, PostDto.class));
        return postDtoList;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : posts)
            postDtoList.add(modelMapper.map(post, PostDto.class));
        return postDtoList;
    }

    @Override
    public String deletePost(Long postId) {
        postRepo.deleteById(postId);
        return "Your Post is removed successfully";
    }
}
