package com.learning.post.service;

import com.learning.post.dto.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, Long postId);

    public CommentDto updateComment(CommentDto commentDto, Long commentId);

    public CommentDto getCommentById(Long commentId);

    public List<CommentDto> getCommentByPostId(Long postId);

    public List<CommentDto> getCommentByUserId();

    public String deleteComment(Long commentId);

}
