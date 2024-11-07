package com.karo.UserAccessManagement.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karo.UserAccessManagement.Entity.AccessRequest;
import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Entity.User;
import com.karo.UserAccessManagement.Enums.RequestStatus;
import com.karo.UserAccessManagement.Service.AccessRequestService;
import com.karo.UserAccessManagement.Service.SoftwareService;
import com.karo.UserAccessManagement.Service.UserService;
import com.karo.UserAccessManagement.dto.AccessRequestDto;
import com.karo.UserAccessManagement.dto.response.AccessRequestResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class AccessRequestController {
    private final AccessRequestService accessRequestService;
    private final UserService userService;
    private final SoftwareService softwareService;


    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<AccessRequestResponseDto> createRequest(
            @RequestBody AccessRequestDto requestDto,
            Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName());
        Software software = softwareService.getSoftwareById(requestDto.getSoftwareId());
        AccessRequest request = accessRequestService.createRequest(requestDto, user, software);
        return ResponseEntity.ok(mapToAccessRequestResponseDto(request));
    }

    @PutMapping("/{requestId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<AccessRequestResponseDto> processRequest(
            @PathVariable Long requestId,
            @RequestParam RequestStatus status,
            Authentication authentication) {
       try {
            User manager = userService.getUserByUsername(authentication.getName());
            AccessRequest request = accessRequestService.processRequest(requestId, status, manager);
            return ResponseEntity.ok(mapToAccessRequestResponseDto(request));
       } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
                
    }


    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<AccessRequestResponseDto>> getPendingRequests(
            @RequestParam(required = false) RequestStatus status) {
        List<AccessRequest> requests = accessRequestService.getPendingRequests(status);
        return ResponseEntity.ok(
                requests.stream()
                        .map(this::mapToAccessRequestResponseDto)
                        .collect(Collectors.toList())
        );
    }

    private AccessRequestResponseDto mapToAccessRequestResponseDto(AccessRequest request) {
        AccessRequestResponseDto dto = new AccessRequestResponseDto();
        dto.setId(request.getId());
        dto.setUsername(request.getUser().getUsername());
        dto.setSoftwareName(request.getSoftware().getName());
        dto.setAccessType(request.getAccessType());
        dto.setReason(request.getReason());
        dto.setStatus(request.getStatus());
        return dto;
    }
}
