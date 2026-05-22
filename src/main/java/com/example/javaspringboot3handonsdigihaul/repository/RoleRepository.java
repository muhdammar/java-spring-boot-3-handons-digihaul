package com.example.javaspringboot3handonsdigihaul.repository;

import com.example.javaspringboot3handonsdigihaul.enums.RoleName;
import com.example.javaspringboot3handonsdigihaul.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByCode(RoleName code);
}

