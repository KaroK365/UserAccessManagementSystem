package com.karo.UserAccessManagement.Controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karo.UserAccessManagement.Entity.AccessRequest;
import com.karo.UserAccessManagement.Enums.RequestStatus;
import com.karo.UserAccessManagement.Service.AccessRequestService;
import com.karo.UserAccessManagement.dto.AccessRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccessRequestController {
    private final AccessRequestService accessRequestService;

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<AccessRequest> createRequest(@RequestBody AccessRequestDto requestDto, Authentication authentication){
        return ResponseEntity.ok(accessRequestService.createRequest(requestDto, authentication.getUsername()));
    }

    @PutMapping("/{requestId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<AccessRequest> processRequest(
            @PathVariable Long requestId,
            @RequestParam RequestStatus status) {
        return ResponseEntity.ok(accessRequestService.processRequest(requestId, status));
    }
}
