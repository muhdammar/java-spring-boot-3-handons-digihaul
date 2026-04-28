package com.example.javaspringboot3handonsdigihaul.shipment.dto;

import com.example.javaspringboot3handonsdigihaul.shipment.ShipmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ShipmentResponse {

    private UUID id;
    private String trackingNumber;
    private String senderName;
    private String senderPhone;
    private String senderAddressLine1;
    private String senderAddressLine2;
    private String senderCity;
    private String senderState;
    private String senderPostcode;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddressLine1;
    private String receiverAddressLine2;
    private String receiverCity;
    private String receiverState;
    private String receiverPostcode;
    private BigDecimal parcelWeightKg;
    private BigDecimal parcelLengthCm;
    private BigDecimal parcelWidthCm;
    private BigDecimal parcelHeightCm;
    private ShipmentStatus status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ShipmentResponse(
            UUID id,
            String trackingNumber,
            String senderName,
            String senderPhone,
            String senderAddressLine1,
            String senderAddressLine2,
            String senderCity,
            String senderState,
            String senderPostcode,
            String receiverName,
            String receiverPhone,
            String receiverAddressLine1,
            String receiverAddressLine2,
            String receiverCity,
            String receiverState,
            String receiverPostcode,
            BigDecimal parcelWeightKg,
            BigDecimal parcelLengthCm,
            BigDecimal parcelWidthCm,
            BigDecimal parcelHeightCm,
            ShipmentStatus status,
            String remarks,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddressLine1 = senderAddressLine1;
        this.senderAddressLine2 = senderAddressLine2;
        this.senderCity = senderCity;
        this.senderState = senderState;
        this.senderPostcode = senderPostcode;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddressLine1 = receiverAddressLine1;
        this.receiverAddressLine2 = receiverAddressLine2;
        this.receiverCity = receiverCity;
        this.receiverState = receiverState;
        this.receiverPostcode = receiverPostcode;
        this.parcelWeightKg = parcelWeightKg;
        this.parcelLengthCm = parcelLengthCm;
        this.parcelWidthCm = parcelWidthCm;
        this.parcelHeightCm = parcelHeightCm;
        this.status = status;
        this.remarks = remarks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public String getSenderAddressLine1() {
        return senderAddressLine1;
    }

    public String getSenderAddressLine2() {
        return senderAddressLine2;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public String getSenderState() {
        return senderState;
    }

    public String getSenderPostcode() {
        return senderPostcode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getReceiverAddressLine1() {
        return receiverAddressLine1;
    }

    public String getReceiverAddressLine2() {
        return receiverAddressLine2;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public String getReceiverPostcode() {
        return receiverPostcode;
    }

    public BigDecimal getParcelWeightKg() {
        return parcelWeightKg;
    }

    public BigDecimal getParcelLengthCm() {
        return parcelLengthCm;
    }

    public BigDecimal getParcelWidthCm() {
        return parcelWidthCm;
    }

    public BigDecimal getParcelHeightCm() {
        return parcelHeightCm;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

