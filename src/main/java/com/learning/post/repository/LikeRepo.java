package com.learning.post.repository;

import com.learning.post.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {

    Like findByPostIdAndUserId(Long postId, Long userId);
}
