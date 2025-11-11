package com.example.user_service.usercontroller;

import com.example.user_service.UserServiceApplication;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin

public class UserController {

    private final UserService userservice;

    @GetMapping("/get")
    public List<User> getAllUsers(){
      return userservice.getAllUsers();
    }

    @PostMapping("/set")
    public User saveuser(@RequestBody User user){
        return userservice.saveUser(user);
    }

    @GetMapping("/get/{id}")
    public Optional<User> getById(@PathVariable Long id){
        return userservice.getUserById(id);
    }

    @GetMapping("/get/{name}")
    public  Optional<User> getByuser(@PathVariable String name){
        return userservice.getUsername(name);
    }
}
