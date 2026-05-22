package com.example.javaspringboot3handonsdigihaul.service;

import com.example.javaspringboot3handonsdigihaul.enums.RoleName;
import com.example.javaspringboot3handonsdigihaul.entity.UserAccount;
import com.example.javaspringboot3handonsdigihaul.enums.ShipmentStatus;
import com.example.javaspringboot3handonsdigihaul.repository.UserAccountRepository;
import com.example.javaspringboot3handonsdigihaul.entity.Shipment;
import com.example.javaspringboot3handonsdigihaul.entity.ShipmentStatusHistory;
import com.example.javaspringboot3handonsdigihaul.exception.ForbiddenOperationException;
import com.example.javaspringboot3handonsdigihaul.exception.ResourceNotFoundException;
import com.example.javaspringboot3handonsdigihaul.repository.ShipmentRepository;
import com.example.javaspringboot3handonsdigihaul.repository.ShipmentStatusHistoryRepository;
import com.example.javaspringboot3handonsdigihaul.dto.shipment.CreateShipmentRequest;
import com.example.javaspringboot3handonsdigihaul.dto.shipment.ShipmentResponse;
import com.example.javaspringboot3handonsdigihaul.dto.shipment.UpdateShipmentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {

    private static final Logger log = LoggerFactory.getLogger(ShipmentService.class);

    private final ShipmentRepository shipmentRepository;
    private final ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;
    private final UserAccountRepository userAccountRepository;

    public ShipmentService(
            ShipmentRepository shipmentRepository,
            ShipmentStatusHistoryRepository shipmentStatusHistoryRepository,
            UserAccountRepository userAccountRepository
    ) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentStatusHistoryRepository = shipmentStatusHistoryRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional(readOnly = true)
    public List<ShipmentResponse> listShipments() {
        log.info("listShipments logging test");
        UserAccount currentUser = getCurrentUser();
        List<Shipment> shipments = isAdmin(currentUser)
                ? shipmentRepository.findAll()
                : shipmentRepository.findAllByCreatedBy(currentUser);

        return shipments.stream().map(this::toResponse).toList();
    }

    @Transactional
    public ShipmentResponse createShipment(CreateShipmentRequest request) {
        validateCreateRequest(request);

        if (shipmentRepository.findByTrackingNumber(request.getTrackingNumber()).isPresent()) {
            throw new IllegalArgumentException("Tracking number already exists");
        }

        UserAccount currentUser = getCurrentUser();

        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(request.getTrackingNumber());
        shipment.setSenderName(request.getSenderName());
        shipment.setSenderPhone(request.getSenderPhone());
        shipment.setSenderAddressLine1(request.getSenderAddressLine1());
        shipment.setSenderAddressLine2(request.getSenderAddressLine2());
        shipment.setSenderCity(request.getSenderCity());
        shipment.setSenderState(request.getSenderState());
        shipment.setSenderPostcode(request.getSenderPostcode());
        shipment.setReceiverName(request.getReceiverName());
        shipment.setReceiverPhone(request.getReceiverPhone());
        shipment.setReceiverAddressLine1(request.getReceiverAddressLine1());
        shipment.setReceiverAddressLine2(request.getReceiverAddressLine2());
        shipment.setReceiverCity(request.getReceiverCity());
        shipment.setReceiverState(request.getReceiverState());
        shipment.setReceiverPostcode(request.getReceiverPostcode());
        shipment.setParcelWeightKg(request.getParcelWeightKg());
        shipment.setParcelLengthCm(request.getParcelLengthCm());
        shipment.setParcelWidthCm(request.getParcelWidthCm());
        shipment.setParcelHeightCm(request.getParcelHeightCm());
        shipment.setRemarks(request.getRemarks());
        shipment.setStatus(ShipmentStatus.CREATED);
        shipment.setCreatedBy(currentUser);

        Shipment saved = shipmentRepository.save(shipment);

        ShipmentStatusHistory history = new ShipmentStatusHistory();
        history.setShipment(saved);
        history.setStatus(ShipmentStatus.CREATED);
        history.setDescription("Shipment created");
        history.setUpdatedBy(currentUser);
        shipmentStatusHistoryRepository.save(history);

        log.info("Shipment {} created by {}", saved.getTrackingNumber(), currentUser.getUsername());
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ShipmentResponse getShipment(UUID id) {
        UserAccount currentUser = getCurrentUser();
        Shipment shipment = getAccessibleShipment(id, currentUser);
        return toResponse(shipment);
    }

    @Transactional
    public ShipmentResponse updateShipment(UUID id, UpdateShipmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        UserAccount currentUser = getCurrentUser();
        Shipment shipment = getAccessibleShipment(id, currentUser);

        ShipmentStatus previousStatus = shipment.getStatus();

        if (request.getSenderName() != null) shipment.setSenderName(request.getSenderName());
        if (request.getSenderPhone() != null) shipment.setSenderPhone(request.getSenderPhone());
        if (request.getSenderAddressLine1() != null) shipment.setSenderAddressLine1(request.getSenderAddressLine1());
        if (request.getSenderAddressLine2() != null) shipment.setSenderAddressLine2(request.getSenderAddressLine2());
        if (request.getSenderCity() != null) shipment.setSenderCity(request.getSenderCity());
        if (request.getSenderState() != null) shipment.setSenderState(request.getSenderState());
        if (request.getSenderPostcode() != null) shipment.setSenderPostcode(request.getSenderPostcode());

        if (request.getReceiverName() != null) shipment.setReceiverName(request.getReceiverName());
        if (request.getReceiverPhone() != null) shipment.setReceiverPhone(request.getReceiverPhone());
        if (request.getReceiverAddressLine1() != null) shipment.setReceiverAddressLine1(request.getReceiverAddressLine1());
        if (request.getReceiverAddressLine2() != null) shipment.setReceiverAddressLine2(request.getReceiverAddressLine2());
        if (request.getReceiverCity() != null) shipment.setReceiverCity(request.getReceiverCity());
        if (request.getReceiverState() != null) shipment.setReceiverState(request.getReceiverState());
        if (request.getReceiverPostcode() != null) shipment.setReceiverPostcode(request.getReceiverPostcode());

        if (request.getParcelWeightKg() != null) shipment.setParcelWeightKg(request.getParcelWeightKg());
        if (request.getParcelLengthCm() != null) shipment.setParcelLengthCm(request.getParcelLengthCm());
        if (request.getParcelWidthCm() != null) shipment.setParcelWidthCm(request.getParcelWidthCm());
        if (request.getParcelHeightCm() != null) shipment.setParcelHeightCm(request.getParcelHeightCm());
        if (request.getRemarks() != null) shipment.setRemarks(request.getRemarks());
        if (request.getStatus() != null) shipment.setStatus(request.getStatus());

        shipment.setUpdatedBy(currentUser);

        Shipment saved = shipmentRepository.save(shipment);

        if (request.getStatus() != null && request.getStatus() != previousStatus) {
            ShipmentStatusHistory history = new ShipmentStatusHistory();
            history.setShipment(saved);
            history.setStatus(request.getStatus());
            history.setDescription("Shipment status updated to " + request.getStatus().name());
            history.setUpdatedBy(currentUser);
            shipmentStatusHistoryRepository.save(history);
        }

        log.info("Shipment {} updated by {}", saved.getTrackingNumber(), currentUser.getUsername());
        return toResponse(saved);
    }

    private Shipment getAccessibleShipment(UUID id, UserAccount currentUser) {
        if (isAdmin(currentUser)) {
            return shipmentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Shipment not found: " + id));
        }

        return shipmentRepository.findByIdAndCreatedBy(id, currentUser)
                .orElseThrow(() -> new ForbiddenOperationException("You can only access your own shipments"));
    }

    private UserAccount getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new IllegalStateException("No authenticated user found");
        }

        return userAccountRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));
    }

    private boolean isAdmin(UserAccount user) {
        return user.getRoles().stream().anyMatch(role -> role.getCode() == RoleName.ADMIN);
    }

    private void validateCreateRequest(CreateShipmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (isBlank(request.getTrackingNumber())) {
            throw new IllegalArgumentException("trackingNumber is required");
        }
        if (isBlank(request.getSenderName()) || isBlank(request.getSenderPhone()) || isBlank(request.getSenderAddressLine1())
                || isBlank(request.getSenderCity()) || isBlank(request.getSenderPostcode())) {
            throw new IllegalArgumentException("sender fields are required");
        }
        if (isBlank(request.getReceiverName()) || isBlank(request.getReceiverPhone()) || isBlank(request.getReceiverAddressLine1())
                || isBlank(request.getReceiverCity()) || isBlank(request.getReceiverPostcode())) {
            throw new IllegalArgumentException("receiver fields are required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private ShipmentResponse toResponse(Shipment shipment) {
        return new ShipmentResponse(
                shipment.getId(),
                shipment.getTrackingNumber(),
                shipment.getSenderName(),
                shipment.getSenderPhone(),
                shipment.getSenderAddressLine1(),
                shipment.getSenderAddressLine2(),
                shipment.getSenderCity(),
                shipment.getSenderState(),
                shipment.getSenderPostcode(),
                shipment.getReceiverName(),
                shipment.getReceiverPhone(),
                shipment.getReceiverAddressLine1(),
                shipment.getReceiverAddressLine2(),
                shipment.getReceiverCity(),
                shipment.getReceiverState(),
                shipment.getReceiverPostcode(),
                shipment.getParcelWeightKg(),
                shipment.getParcelLengthCm(),
                shipment.getParcelWidthCm(),
                shipment.getParcelHeightCm(),
                shipment.getStatus(),
                shipment.getRemarks(),
                shipment.getCreatedAt(),
                shipment.getUpdatedAt()
        );
    }
}

