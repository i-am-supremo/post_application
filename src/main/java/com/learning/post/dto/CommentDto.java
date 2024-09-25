package com.learning.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.post.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CommentDto {

    Long id;

    @NotEmpty(message = "Comment can not be empty")
    private String comment;

    private LocalDate createdOn;

    private LocalDate modifiedOn;

    private String isEdited;
}
