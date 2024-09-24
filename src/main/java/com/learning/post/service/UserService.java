package com.learning.post.service;

import com.learning.post.dto.SignupDto;
import com.learning.post.dto.UserDto;
import com.learning.post.entity.User;

import java.util.List;

public interface UserService {

    public UserDto updateUser(UserDto userDto, Long userId);

    public UserDto getUserById(Long userId);

    public List<UserDto> getAllUsers();

    public String deleteUser(Long userId);

    public User getUserByIdJWT(Long userId);
}
