package com.learning.post.service.impl;

import com.learning.post.dto.CommentDto;
import com.learning.post.entity.Comment;
import com.learning.post.entity.Post;
import com.learning.post.entity.User;
import com.learning.post.exception.ResourceNotFoundException;
import com.learning.post.repository.CommentRepo;
import com.learning.post.repository.PostRepo;
import com.learning.post.repository.UserRepo;
import com.learning.post.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("No Post","id",postId));
        User userToBeSaved = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("No User","id",postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setCommentedPost(post);
        comment.setUserWhoCommented(userToBeSaved);
        comment.setCreatedOn(LocalDate.now());
        comment.setModifiedOn(LocalDate.now());
        comment.setIsEdited("N");
        return modelMapper.map(commentRepo.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("No comment","id",commentId));
        comment.setComment(commentDto.getComment());
        comment.setIsEdited("Y");
        comment.setModifiedOn(LocalDate.now());
        return modelMapper.map(commentRepo.save(comment), CommentDto.class);
    }

    @Override
    public String deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
        return "Comment delete successfully";
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return modelMapper.map(commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("No Comment","ID",commentId)), CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {
        Post post = Post.builder().id(postId).build();
        List<Comment> comments = commentRepo.findByCommentedPost(post);
        List<CommentDto> commentDto = new ArrayList<>();
        for(Comment comment : comments)
            commentDto.add(modelMapper.map(comment, CommentDto.class));
        return commentDto;
    }

    @Override
    public List<CommentDto> getCommentByUserId() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToBeSaved = User.builder().id(user.getId()).build();
        List<Comment> comments = commentRepo.findByUserWhoCommented(userToBeSaved);
        List<CommentDto> commentDto = new ArrayList<>();
        for(Comment comment : comments)
            commentDto.add(modelMapper.map(comment, CommentDto.class));
        return commentDto;
    }
}
