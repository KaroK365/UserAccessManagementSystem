package com.karo.UserAccessManagement.Service;
import org.springframework.stereotype.Service;

import com.karo.UserAccessManagement.Entity.Software;
import com.karo.UserAccessManagement.Exception.ResourceNotFoundException;
import com.karo.UserAccessManagement.Repository.SoftwareRepository;
import com.karo.UserAccessManagement.dto.SoftwareCreationDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoftwareService {

    private final SoftwareRepository softwareRepository;
    
    public Software createSoftware(SoftwareCreationDto softwareDto){
        Software software = new Software();
        software.setName(softwareDto.getName());
        software.setDescription(softwareDto.getName());
        software.setAccessLevels(softwareDto.getAccessLevels());
        return  softwareRepository.save(software);
    }

    public Software getSoftwareById(Long id) {
        return softwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software not found"));
    }

}
