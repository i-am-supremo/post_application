package com.learning.post.controller;

import com.learning.post.dto.CommentDto;
import com.learning.post.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/save/{postId}")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("/getCommentById/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.CREATED);
    }

    @GetMapping("/getCommentByPostId/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/getCommentByUserId")
    public ResponseEntity<List<CommentDto>> getCommentByUserId() {
        return new ResponseEntity<>(commentService.getCommentByUserId(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }

    @PostMapping("/update/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.updateComment(commentDto, commentId), HttpStatus.OK);
    }
}
