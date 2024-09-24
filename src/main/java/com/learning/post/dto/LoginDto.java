package com.learning.post.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty(message = "Email Cannot be empty")
    @Email(message = "Please provide correct format email")
    String email;
    @NotEmpty(message = "Password cannot be empty")
    String password;
}
