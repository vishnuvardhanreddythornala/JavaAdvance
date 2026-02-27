package com.cap.Controller;

import com.cap.Model.User;
import com.cap.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    //@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }
    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "userList";
    }
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "userDetail";
    }
    @GetMapping("/addUser")
    public String  showUserForm(){
        return "addUser";
    }
    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String email){
        Long newId = (long) (Math.random() * 1000);
        userService.addUser(new User(newId, name, email));
        return "redirect:/users";
    }
}
