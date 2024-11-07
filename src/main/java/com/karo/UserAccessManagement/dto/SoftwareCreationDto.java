package com.karo.UserAccessManagement.dto;

import java.util.Set;

import com.karo.UserAccessManagement.Enums.AccessLevel;

import lombok.Data;

@Data
public class SoftwareCreationDto {
    private String name;
    private String description;
    private Set<AccessLevel> accessLevels;
}
