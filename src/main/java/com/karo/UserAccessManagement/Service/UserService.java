package com.karo.UserAccessManagement.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Enums.UserRole;
import com.karo.UserAccessManagement.Repository.UserRepository;
import com.karo.UserAccessManagement.dto.UserRegistrationDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto registrationDto){
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(UserRole.EMPLOYEE);
        return userRepository.save(user);
    }
}
