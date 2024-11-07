package com.karo.UserAccessManagement.dto.response;

import com.karo.UserAccessManagement.Enums.UserRole;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private UserRole role;
}
