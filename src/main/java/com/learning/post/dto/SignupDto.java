package com.learning.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SignupDto {
    @NotEmpty
    @Min(value = 3, message = "Name must be of at least 3 chars")
    private String name;
    @Email(message = "Please provide a valid email")
    private String email;
    @Min(value = 6, message = "Password must be greater than 6 chars")
    private String password;
    private LocalDate joinedOn;
}
