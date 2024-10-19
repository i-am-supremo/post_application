package com.learning.post.controller;

import com.learning.post.dto.UserDto;
import com.learning.post.service.UserService;
import com.learning.post.service.impl.CSVServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CSVServiceImpl csvService;

    @Operation(summary = "Update the User Details")
    @PostMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(userDto, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Get the User Details from user id")
    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUserById() {
        return new ResponseEntity<>(userService.getUserById(), HttpStatus.OK);
    }

    @Operation(summary = "Get All Users")
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Delete User by id (Admin access only)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Users in CSV (Admin access only)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/users/csv")
    public void downloadUserCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename = user.csv");
        List<UserDto> userDtos = userService.getAllUsers();
        csvService.writeUsersToCsv(response.getWriter(),userDtos);
    }
}
