package com.learning.post.controller;

import com.learning.post.dto.LoginDto;
import com.learning.post.dto.SignupDto;
import com.learning.post.dto.UserDto;
import com.learning.post.service.impl.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Operation(summary = "Signup the user")
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup( @RequestBody SignupDto signupDto)
    {
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Login the user")
    @PostMapping("/login")
    public  ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto)
    {
        String token = authService.login(loginDto);
        return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
    }
}
