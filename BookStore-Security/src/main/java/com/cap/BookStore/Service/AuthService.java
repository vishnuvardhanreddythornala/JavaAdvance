package com.cap.BookStore.Service;

import com.cap.BookStore.dto.AuthResponse;
import com.cap.BookStore.dto.LoginRequest;
import com.cap.BookStore.dto.RegisterRequest;
import com.cap.BookStore.entity.Role;
import com.cap.BookStore.entity.User;
import com.cap.BookStore.repository.UserRepository;
import com.cap.BookStore.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private ModelMapper modelMapper;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    public String register(RegisterRequest request){
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

//    public AuthResponse login(LoginRequest request){
//        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("User not found!"));
//
//        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
//            throw new RuntimeException("Invalid Credential");
//        }
//
//        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
//        return new AuthResponse(token, user.getUsername(), user.getRole().name());
//
//    }
}