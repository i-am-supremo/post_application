package com.learning.post.repository;

import com.learning.post.entity.Category;
import com.learning.post.entity.Post;
import com.learning.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);
}
