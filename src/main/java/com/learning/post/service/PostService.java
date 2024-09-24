package com.learning.post.service;

import com.learning.post.dto.PostDto;
import com.learning.post.entity.Post;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    public PostDto updatePost(PostDto postDto, Long postId);

    public PostDto getPostById(Long postId);

    public List<PostDto> getPostByUserId(Long userId);

    public List<PostDto> getPostByCategoryId(Long categoryId);

    public List<PostDto> getAllPosts();

    public String deletePost(Long postId);

}
