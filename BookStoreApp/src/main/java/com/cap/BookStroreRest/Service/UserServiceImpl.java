package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.LoginRequest;
import com.cap.BookStroreRest.DataTransferObject.LoginResponse;
import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.cap.BookStroreRest.DataTransferObject.UserPageResponse;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        LoginResponse response = new LoginResponse();

        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());

        // Dummy tokens (for now)
        response.setAccessToken("access-token-12345");
        response.setRefreshToken("refresh-token-67890");

        return response;
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
    @Override
    public UserPageResponse getUsersWithPagination(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> userPage = userRepository.findAll(pageable);

        List<UserDto> userDtos = userPage.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();

        UserPageResponse response = new UserPageResponse();
        response.setContent(userDtos);
        response.setPageNumber(userPage.getNumber());
        response.setPageSize(userPage.getSize());
        response.setTotalElements(userPage.getTotalElements());
        response.setTotalPages(userPage.getTotalPages());
        response.setLast(userPage.isLast());

        return response;
    }
}