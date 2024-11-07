package com.karo.UserAccessManagement.Entity;

import com.karo.UserAccessManagement.Enums.AccessLevel;
import com.karo.UserAccessManagement.Enums.RequestStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "request")
@Data
public class AccessRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessType;


    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
