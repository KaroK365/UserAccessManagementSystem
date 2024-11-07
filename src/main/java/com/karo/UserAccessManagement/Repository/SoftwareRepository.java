package com.karo.UserAccessManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karo.UserAccessManagement.Entity.Software;

public interface SoftwareRepository extends JpaRepository<Software,Long> {

}
