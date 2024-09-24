package com.learning.post.service.impl;

import com.learning.post.dto.SignupDto;
import com.learning.post.dto.UserDto;
import com.learning.post.entity.User;
import com.learning.post.exception.ResourceNotFoundException;
import com.learning.post.repository.UserRepo;
import com.learning.post.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("No User", "Email id", 404L));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepo.findById(userId).get();
        User userToBeUpdated = modelMapper.map(userDto, User.class);
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setJoinedOn(user.getJoinedOn());
        userToBeUpdated.setId(userId);
        return modelMapper.map(userRepo.save(userToBeUpdated), UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return modelMapper.map(userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user", "id", userId)), UserDto.class);
    }

    @Override
    public User getUserByIdJWT(Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user", "id", userId));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users)
            userDtoList.add(modelMapper.map(user, UserDto.class));
        return userDtoList;
    }

    @Override
    public String deleteUser(Long userId) {
        userRepo.deleteById(userId);
        return "User Removed";
    }
}
