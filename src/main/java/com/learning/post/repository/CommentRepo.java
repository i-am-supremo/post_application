package com.learning.post.repository;

import com.learning.post.entity.Comment;
import com.learning.post.entity.Post;
import com.learning.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    public List<Comment> findByCommentedPost(Post post);

    List<Comment> findByUserWhoCommented(User user);


}
