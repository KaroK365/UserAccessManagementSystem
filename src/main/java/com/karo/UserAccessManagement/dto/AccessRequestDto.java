package com.karo.UserAccessManagement.dto;

import com.karo.UserAccessManagement.Enums.AccessLevel;

import lombok.Data;


@Data
public class AccessRequestDto {
    private Long softwareId;
    private AccessLevel accessType;
    private String reason;
}
