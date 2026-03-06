package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.LoginRequest;
import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.DataTransferObject.UserPageResponse;
import com.cap.BookStroreRest.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //Register User
    @PostMapping("/register")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }

    //Login User
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }

    //Get All Users
    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    //Get User By Id
    @GetMapping("/getById/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // Update User
    @PutMapping("/updateUser/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }
    // Delete User
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    //pagination
    @GetMapping("/pagination")
    public UserPageResponse getUsersWithPagination(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {

        return userService.getUsersWithPagination(pageNumber, pageSize);
    }
}