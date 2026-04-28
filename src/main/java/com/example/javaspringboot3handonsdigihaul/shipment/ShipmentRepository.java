package com.example.javaspringboot3handonsdigihaul.shipment;

import com.example.javaspringboot3handonsdigihaul.auth.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    Optional<Shipment> findByIdAndCreatedBy(UUID id, UserAccount userAccount);

    List<Shipment> findAllByCreatedBy(UserAccount userAccount);
}

