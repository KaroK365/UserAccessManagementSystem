package com.karo.UserAccessManagement.dto.response;

import java.util.Set;

import com.karo.UserAccessManagement.Enums.AccessLevel;

import lombok.Data;

@Data
public class SoftwareResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<AccessLevel> accessLevels;
}