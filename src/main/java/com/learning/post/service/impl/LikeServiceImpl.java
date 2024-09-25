package com.learning.post.service.impl;

import com.learning.post.entity.Like;
import com.learning.post.entity.Post;
import com.learning.post.entity.User;
import com.learning.post.repository.LikeRepo;
import com.learning.post.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepo likeRepo;


    @Override
    public String likePost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = Post.builder().id(postId).build();
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepo.save(like);
        return "Post Liked";
    }

    @Override
    public String unlikePost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Like like = likeRepo.findByPostIdAndUserId(postId, user.getId());
        likeRepo.delete(like);
        return "Post unliked";
    }
}
