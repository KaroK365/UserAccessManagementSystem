package com.karo.UserAccessManagement.Service;

import org.springframework.stereotype.Service;

import com.karo.UserAccessManagement.Entity.AccessRequest;
import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Enums.RequestStatus;
import com.karo.UserAccessManagement.Repository.AccessRequestRepository;
import com.karo.UserAccessManagement.Repository.SoftwareRepository;
import com.karo.UserAccessManagement.Repository.UserRepository;
import com.karo.UserAccessManagement.dto.AccessRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessRequestService {
    private final AccessRequestRepository accessRequestRepository;
    private final UserRepository userRepository;
    private final SoftwareRepository softwareRepository;

    public AccessRequest createRequest(AccessRequestDto requestDto, String username){
        User user = userRepository.findByUsername(username)
                                    .orElseThrow(() -> new RuntimeException("User not found"));

        Software software = softwareRepository.findById(requestDto.getSoftwareId())
                                    .orElseThrow(() -> new RuntimeException("Software not found"));

        AccessRequest request =  new AccessRequest();
        request.setUser(user);
        request.setSoftware(software);
        request.setAccessType(requestDto.getAccessType());
        request.setReason(requestDto.getReason());
        request.setStatus(RequestStatus.PENDING);

        return accessRequestRepository.save(request);
    }

    public AccessRequest processRequest(Long requestId, RequestStatus status) {
        AccessRequest request = accessRequestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found"));
            
        request.setStatus(status);
        return accessRequestRepository.save(request);
    }
}
