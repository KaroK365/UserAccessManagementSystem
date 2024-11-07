package com.karo.UserAccessManagement.Entity;

import java.util.Set;

import com.karo.UserAccessManagement.Enums.AccessLevel;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "software")
@Data
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<AccessLevel> accessLevels;

    @OneToMany(mappedBy = "software")
    private Set<AccessRequest> accessRequests;
}
