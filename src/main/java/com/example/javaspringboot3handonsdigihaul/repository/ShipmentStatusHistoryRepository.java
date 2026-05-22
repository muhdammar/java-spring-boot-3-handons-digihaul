package com.example.javaspringboot3handonsdigihaul.repository;

import com.example.javaspringboot3handonsdigihaul.entity.ShipmentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShipmentStatusHistoryRepository extends JpaRepository<ShipmentStatusHistory, UUID> {

    List<ShipmentStatusHistory> findByShipmentIdOrderByEventTimeAsc(UUID shipmentId);
}

