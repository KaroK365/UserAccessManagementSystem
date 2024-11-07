package com.karo.UserAccessManagement.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Service.SoftwareService;
import com.karo.UserAccessManagement.dto.SoftwareCreationDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/software")
@RequiredArgsConstructor
public class SoftwareController {
    private final SoftwareService softwareService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Software> createSoftwate(@RequestBody SoftwareCreationDto softwareDto){
        return ResponseEntity.ok(softwareService.createSoftware(softwareDto));
    }
}
