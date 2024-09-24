package com.learning.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.post.entity.Category;
import com.learning.post.entity.Comment;
import com.learning.post.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PostDto {

    private Long id;

    @NotEmpty(message = "Title of post cannot be empty")
    @Min(value = 3, message = "The title must be of 3 characters")
    private String title;

    @NotEmpty(message = "Description of Post cannot be empty")
    @Min(value = 10, message = "The description must be of 10 characters")
    private String description;

    private LocalDate createdOn;

    private LocalDate modifiedOn;

    private List<Comment> comments;
}
