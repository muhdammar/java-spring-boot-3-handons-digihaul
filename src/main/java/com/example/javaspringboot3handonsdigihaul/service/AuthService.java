package com.example.javaspringboot3handonsdigihaul.service;

import com.example.javaspringboot3handonsdigihaul.dto.auth.AuthResponse;
import com.example.javaspringboot3handonsdigihaul.dto.auth.LoginRequest;
import com.example.javaspringboot3handonsdigihaul.dto.auth.MessageResponse;
import com.example.javaspringboot3handonsdigihaul.dto.auth.RegisterRequest;
import com.example.javaspringboot3handonsdigihaul.entity.Role;
import com.example.javaspringboot3handonsdigihaul.entity.UserAccount;
import com.example.javaspringboot3handonsdigihaul.enums.RoleName;
import com.example.javaspringboot3handonsdigihaul.repository.RoleRepository;
import com.example.javaspringboot3handonsdigihaul.repository.UserAccountRepository;
import com.example.javaspringboot3handonsdigihaul.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserAccountRepository userAccountRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        validateRegisterRequest(request);

        if (userAccountRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userAccountRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role userRole = roleRepository.findByCode(RoleName.USER)
                .orElseGet(() -> roleRepository.save(new Role(RoleName.USER, "Normal User")));

        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.getRoles().add(userRole);

        UserAccount savedUser = userAccountRepository.save(user);
        log.info("Registered new user: {}", savedUser.getUsername());

        User springUser = new User(
                savedUser.getUsername(),
                savedUser.getPasswordHash(),
                savedUser.isActive(),
                true,
                true,
                true,
                savedUser.getRoles().stream()
                        .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role.getCode().name()))
                        .collect(Collectors.toSet())
        );

        String token = jwtService.generateToken(springUser);
        String role = savedUser.getRoles().stream().map(r -> r.getCode().name()).findFirst()
                .orElse("USER");
        return new AuthResponse(token, "Bearer", jwtService.getJwtExpiration(), savedUser.getUsername(), role);
    }

    public AuthResponse login(LoginRequest request) {
        if (request == null || isBlank(request.getUsername()) || isBlank(request.getPassword())) {
            throw new IllegalArgumentException("Username and password are required");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserAccount user = userAccountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        User springUser = new User(
                user.getUsername(),
                user.getPasswordHash(),
                user.isActive(),
                true,
                true,
                true,
                user.getRoles().stream()
                        .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role.getCode().name()))
                        .collect(Collectors.toSet())
        );

        String token = jwtService.generateToken(springUser);
        String role = user.getRoles().stream()
                .map(r -> r.getCode().name())
                .findFirst()
                .orElse("USER");
        log.info("User logged in: {}", user.getUsername());

        return new AuthResponse(token, "Bearer", jwtService.getJwtExpiration(), user.getUsername(), role);
    }

    public MessageResponse logout() {
        // Stateless JWT logout is handled on client side by discarding the token.
        return new MessageResponse("Logout successful");
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (isBlank(request.getUsername())) {
            throw new IllegalArgumentException("Username is required");
        }
        if (isBlank(request.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
        if (isBlank(request.getPassword())) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

