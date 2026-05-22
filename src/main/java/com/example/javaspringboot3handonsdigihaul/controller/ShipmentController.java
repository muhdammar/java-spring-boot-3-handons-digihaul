package com.example.javaspringboot3handonsdigihaul.controller;

import com.example.javaspringboot3handonsdigihaul.service.ShipmentService;
import com.example.javaspringboot3handonsdigihaul.dto.shipment.CreateShipmentRequest;
import com.example.javaspringboot3handonsdigihaul.dto.shipment.ShipmentResponse;
import com.example.javaspringboot3handonsdigihaul.dto.shipment.UpdateShipmentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> listShipments() {
        return ResponseEntity.ok(shipmentService.listShipments());
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody CreateShipmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.createShipment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipment(@PathVariable UUID id) {
        return ResponseEntity.ok(shipmentService.getShipment(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponse> updateShipment(@PathVariable UUID id, @RequestBody UpdateShipmentRequest request) {
        return ResponseEntity.ok(shipmentService.updateShipment(id, request));
    }
}

