package com.learning.post.service.impl;

import com.learning.post.dto.LoginDto;
import com.learning.post.dto.SignupDto;
import com.learning.post.dto.UserDto;
import com.learning.post.entity.User;
import com.learning.post.entity.enums.Role;
import com.learning.post.exception.EmailAlreadyPresentException;
import com.learning.post.exception.ResourceNotFoundException;
import com.learning.post.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public UserDto signup(SignupDto signupDto) {

        Optional<User> user = userRepo.findByEmail(signupDto.getEmail());
        if (user.isPresent())
            throw new EmailAlreadyPresentException(signupDto.getEmail());

        User userToBeSaved = modelMapper.map(signupDto, User.class);
        userToBeSaved.setJoinedOn(LocalDate.now());
        userToBeSaved.setRole(Role.USER);
        userToBeSaved.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        return modelMapper.map(userRepo.save(userToBeSaved), UserDto.class);

    }


    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
