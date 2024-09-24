package com.learning.post.controller;

import com.learning.post.dto.CommentDto;
import com.learning.post.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Save the comment")
    @PostMapping("/save/{postId}")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @Operation(summary = "Get the comment by comment id")
    @GetMapping("/getCommentById/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.CREATED);
    }

    @Operation(summary = "Get the comment by post id")
    @GetMapping("/getCommentByPostId/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentByPostId(postId), HttpStatus.OK);
    }

    @Operation(summary = "Get the comment by user id")
    @GetMapping("/getCommentByUserId")
    public ResponseEntity<List<CommentDto>> getCommentByUserId() {
        return new ResponseEntity<>(commentService.getCommentByUserId(), HttpStatus.OK);
    }

    @Operation(summary = "Delete the comment by comment id")
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }

    @Operation(summary = "Update the comment by comment id")
    @PostMapping("/update/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.updateComment(commentDto, commentId), HttpStatus.OK);
    }
}
