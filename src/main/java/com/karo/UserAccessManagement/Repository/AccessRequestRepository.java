package com.karo.UserAccessManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karo.UserAccessManagement.Entity.AccessRequest;
import com.karo.UserAccessManagement.Enums.RequestStatus;


public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    List<AccessRequest> findByStatus(RequestStatus status);
}
