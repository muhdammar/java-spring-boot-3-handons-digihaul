package com.example.javaspringboot3handonsdigihaul.entity;

import com.example.javaspringboot3handonsdigihaul.enums.RoleName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 30)
    private RoleName code;

    @Column(nullable = false, length = 100)
    private String name;

    public Role() {
    }

    public Role(RoleName code, String name) {
        this.code = code;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public RoleName getCode() {
        return code;
    }

    public void setCode(RoleName code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

