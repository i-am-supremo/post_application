package com.learning.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.post.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserDto {

    Long id;
    @NotEmpty
    @Min(value = 3, message = "Name must be of at least 3 chars")
    private String name;
    @Email(message = "Please provide a valid email")
    private String email;
    private LocalDate joinedOn;
    private List<Post> userPosts;
}
