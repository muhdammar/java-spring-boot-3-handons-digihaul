package com.example.javaspringboot3handonsdigihaul.entity;

import com.example.javaspringboot3handonsdigihaul.enums.ShipmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tracking_number", nullable = false, unique = true, length = 40)
    private String trackingNumber;

    @Column(name = "sender_name", nullable = false, length = 120)
    private String senderName;

    @Column(name = "sender_phone", nullable = false, length = 30)
    private String senderPhone;

    @Column(name = "sender_address_line1", nullable = false, length = 200)
    private String senderAddressLine1;

    @Column(name = "sender_address_line2", length = 200)
    private String senderAddressLine2;

    @Column(name = "sender_city", nullable = false, length = 80)
    private String senderCity;

    @Column(name = "sender_state", length = 80)
    private String senderState;

    @Column(name = "sender_postcode", nullable = false, length = 20)
    private String senderPostcode;

    @Column(name = "receiver_name", nullable = false, length = 120)
    private String receiverName;

    @Column(name = "receiver_phone", nullable = false, length = 30)
    private String receiverPhone;

    @Column(name = "receiver_address_line1", nullable = false, length = 200)
    private String receiverAddressLine1;

    @Column(name = "receiver_address_line2", length = 200)
    private String receiverAddressLine2;

    @Column(name = "receiver_city", nullable = false, length = 80)
    private String receiverCity;

    @Column(name = "receiver_state", length = 80)
    private String receiverState;

    @Column(name = "receiver_postcode", nullable = false, length = 20)
    private String receiverPostcode;

    @Column(name = "parcel_weight_kg", precision = 10, scale = 3)
    private BigDecimal parcelWeightKg;

    @Column(name = "parcel_length_cm", precision = 10, scale = 2)
    private BigDecimal parcelLengthCm;

    @Column(name = "parcel_width_cm", precision = 10, scale = 2)
    private BigDecimal parcelWidthCm;

    @Column(name = "parcel_height_cm", precision = 10, scale = 2)
    private BigDecimal parcelHeightCm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ShipmentStatus status = ShipmentStatus.CREATED;

    @Column(length = 500)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserAccount createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id")
    private UserAccount updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Shipment() {
    }

    public UUID getId() {
        return id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderAddressLine1() {
        return senderAddressLine1;
    }

    public void setSenderAddressLine1(String senderAddressLine1) {
        this.senderAddressLine1 = senderAddressLine1;
    }

    public String getSenderAddressLine2() {
        return senderAddressLine2;
    }

    public void setSenderAddressLine2(String senderAddressLine2) {
        this.senderAddressLine2 = senderAddressLine2;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getSenderPostcode() {
        return senderPostcode;
    }

    public void setSenderPostcode(String senderPostcode) {
        this.senderPostcode = senderPostcode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddressLine1() {
        return receiverAddressLine1;
    }

    public void setReceiverAddressLine1(String receiverAddressLine1) {
        this.receiverAddressLine1 = receiverAddressLine1;
    }

    public String getReceiverAddressLine2() {
        return receiverAddressLine2;
    }

    public void setReceiverAddressLine2(String receiverAddressLine2) {
        this.receiverAddressLine2 = receiverAddressLine2;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverPostcode() {
        return receiverPostcode;
    }

    public void setReceiverPostcode(String receiverPostcode) {
        this.receiverPostcode = receiverPostcode;
    }

    public BigDecimal getParcelWeightKg() {
        return parcelWeightKg;
    }

    public void setParcelWeightKg(BigDecimal parcelWeightKg) {
        this.parcelWeightKg = parcelWeightKg;
    }

    public BigDecimal getParcelLengthCm() {
        return parcelLengthCm;
    }

    public void setParcelLengthCm(BigDecimal parcelLengthCm) {
        this.parcelLengthCm = parcelLengthCm;
    }

    public BigDecimal getParcelWidthCm() {
        return parcelWidthCm;
    }

    public void setParcelWidthCm(BigDecimal parcelWidthCm) {
        this.parcelWidthCm = parcelWidthCm;
    }

    public BigDecimal getParcelHeightCm() {
        return parcelHeightCm;
    }

    public void setParcelHeightCm(BigDecimal parcelHeightCm) {
        this.parcelHeightCm = parcelHeightCm;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public UserAccount getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserAccount createdBy) {
        this.createdBy = createdBy;
    }

    public UserAccount getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserAccount updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

