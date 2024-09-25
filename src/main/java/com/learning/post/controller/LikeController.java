package com.learning.post.controller;

import com.learning.post.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Operation(summary = "User likes the post")
    @PostMapping("/like/{postId}")
    public ResponseEntity<String> likePost(@PathVariable Long postId)
    {
        return new ResponseEntity<>(likeService.likePost(postId), HttpStatus.CREATED);
    }

    @Operation(summary = "User unlikes the Post")
    @PostMapping("/unlike/{postId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId)
    {
        return new ResponseEntity<>(likeService.unlikePost(postId), HttpStatus.CREATED);
    }


}
