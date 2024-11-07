package com.karo.UserAccessManagement.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Service.UserService;
import com.karo.UserAccessManagement.dto.UserRegistrationDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto){
        return ResponseEntity.ok(userService.registerUser(registrationDto));
    }
}
