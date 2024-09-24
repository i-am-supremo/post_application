package com.learning.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.post.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CategoryDto {
     @NotEmpty(message = "Category name cannot be Empty")
     String categoryName;
     @NotEmpty(message = "Category Description cannot be empty")
     String categoryDescription;
     List<Post> posts;
}
