package com.karo.UserAccessManagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karo.UserAccessManagement.Entity.AccessRequest;
import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Enums.RequestStatus;
import com.karo.UserAccessManagement.Enums.UserRole;
import com.karo.UserAccessManagement.Exception.ResourceNotFoundException;
import com.karo.UserAccessManagement.Repository.AccessRequestRepository;
import com.karo.UserAccessManagement.dto.AccessRequestDto;
import com.karo.UserAccessManagement.utility.ValidationUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessRequestService {
    private final AccessRequestRepository accessRequestRepository;
    private final ValidationUtil validationUtil;

    public AccessRequest createRequest(AccessRequestDto requestDto, User user, Software software) {
        validationUtil.validateAccessRequest(requestDto, software);
        AccessRequest request = new AccessRequest();
        request.setUser(user);
        request.setSoftware(software);
        request.setAccessType(requestDto.getAccessType());
        request.setReason(requestDto.getReason());
        request.setStatus(RequestStatus.PENDING);
        return accessRequestRepository.save(request);
    }

    public AccessRequest processRequest(Long requestId, RequestStatus status, User manager) throws Exception {
        AccessRequest request = accessRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Access request not found"));
        validationUtil.validateUserRole(manager, UserRole.MANAGER);
        request.setStatus(status);
        return accessRequestRepository.save(request);
    }

    public List<AccessRequest> getPendingRequests(RequestStatus status) {
        if (status == null) {
            return accessRequestRepository.findAll();
        } else {
            return accessRequestRepository.findByStatus(status);
        }
    }
}
