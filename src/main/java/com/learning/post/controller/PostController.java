package com.learning.post.controller;

import com.learning.post.dto.PostDto;
import com.learning.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/save/{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Long categoryId)
    {
        return new ResponseEntity<>(postService.createPost(postDto, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/getByPostId/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId)
    {
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<PostDto>> getPostByUserId(){
        return new ResponseEntity<>(postService.getPostByUserId(),HttpStatus.OK);
    }

    @GetMapping("/getByCategoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Long categoryId){
        return new ResponseEntity<>(postService.getPostByCategoryId(categoryId),HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        return new ResponseEntity<>(postService.deletePost(postId),HttpStatus.OK);
    }

    @PostMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Long postId){
        return new ResponseEntity<>(postService.updatePost(postDto, postId),HttpStatus.OK);
    }

}
