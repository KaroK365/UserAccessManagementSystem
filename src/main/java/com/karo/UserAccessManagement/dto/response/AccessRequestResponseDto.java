package com.karo.UserAccessManagement.dto.response;

import com.karo.UserAccessManagement.Enums.AccessLevel;
import com.karo.UserAccessManagement.Enums.RequestStatus;

import lombok.Data;

@Data
public class AccessRequestResponseDto {
    private Long id;
    private String username;
    private String softwareName;
    private AccessLevel accessType;
    private String reason;
    private RequestStatus status;
}
