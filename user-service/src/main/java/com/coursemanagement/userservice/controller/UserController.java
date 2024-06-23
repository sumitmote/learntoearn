package com.coursemanagement.userservice.controller;

import com.coursemanagement.userservice.model.User;
import com.coursemanagement.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user, @RequestParam String role) {
        userService.saveUser(user, role);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("Welcome, " + userDetails.getUsername());
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

