package com.example.javaspringboot3handonsdigihaul.entity;

import com.example.javaspringboot3handonsdigihaul.enums.RoleName;
import com.example.javaspringboot3handonsdigihaul.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleDataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RoleDataInitializer.class);

    private final RoleRepository roleRepository;

    public RoleDataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        ensureRole(RoleName.ADMIN, "Administrator");
        ensureRole(RoleName.USER, "Normal User");
    }

    private void ensureRole(RoleName roleName, String displayName) {
        roleRepository.findByCode(roleName)
                .orElseGet(() -> {
                    log.info("Seeding role: {}", roleName);
                    return roleRepository.save(new Role(roleName, displayName));
                });
    }
}

