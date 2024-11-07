package com.karo.UserAccessManagement.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Repository.SoftwareRepository;
import com.karo.UserAccessManagement.dto.SoftwareCreationDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoftwareService {

    private final SoftwareRepository softwareRepository;
    
    public ResponseEntity<Software> createSoftware(SoftwareCreationDto softwareDto){
        Software software = new Software();
        software.setName(softwareDto.getName());
        software.setDescription(softwareDto.getName());
        software.setAccessLevels(softwareDto.getAccessLevels());

        try {
            Software savedSoftware = softwareRepository.save(software);
            return new ResponseEntity<>(savedSoftware,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
