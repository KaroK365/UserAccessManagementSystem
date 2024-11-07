package com.karo.UserAccessManagement.Controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Service.UserService;
import com.karo.UserAccessManagement.dto.UserRegistrationDto;
import com.karo.UserAccessManagement.dto.response.UserResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationDto registrationDto){
        User user = userService.registerUser(registrationDto);
        return ResponseEntity.ok(mapToUserResponseDto(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getUsername());
        return ResponseEntity.ok(mapToUserResponseDto(user));
    }



    private UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}
