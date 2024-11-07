package com.karo.UserAccessManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karo.UserAccessManagement.Entity.User;



public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
