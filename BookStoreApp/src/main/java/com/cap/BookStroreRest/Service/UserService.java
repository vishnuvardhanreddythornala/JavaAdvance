package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.LoginRequest;
import com.cap.BookStroreRest.DataTransferObject.LoginResponse;
import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.DataTransferObject.UserPageResponse;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    LoginResponse loginUser(LoginRequest loginRequest);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    UserPageResponse getUsersWithPagination(int pageNumber, int pageSize);
}