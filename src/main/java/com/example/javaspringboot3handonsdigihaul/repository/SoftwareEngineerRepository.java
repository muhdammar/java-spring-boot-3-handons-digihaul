package com.example.javaspringboot3handonsdigihaul.repository;

import com.example.javaspringboot3handonsdigihaul.entity.SoftwareEngineer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareEngineerRepository
        extends JpaRepository<SoftwareEngineer, Integer> {

}
