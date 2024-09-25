package com.learning.post.service;

import org.springframework.http.ResponseEntity;

public interface LikeService {

    String likePost(Long postId);

    String unlikePost(Long postId);
}
