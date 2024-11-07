package com.karo.UserAccessManagement.utility;

import org.springframework.stereotype.Component;

import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Enums.UserRole;
import com.karo.UserAccessManagement.dto.AccessRequestDto;


@Component
public class ValidationUtil {
    public void validateAccessRequest(AccessRequestDto requestDto, Software software) {
        if (!software.getAccessLevels().contains(requestDto.getAccessType())) {
            throw new IllegalArgumentException(
                "Requested access level is not available for this software"
            );
        }
    }


    public void validateUserRole(User user, UserRole requiredRole) throws Exception {
        if (user.getRole() != requiredRole) {
            throw new Exception(
                "User does not have required role: " + requiredRole
            );
        }
    }
}
