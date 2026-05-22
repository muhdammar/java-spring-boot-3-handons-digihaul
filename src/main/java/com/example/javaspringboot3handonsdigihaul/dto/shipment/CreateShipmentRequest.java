package com.example.javaspringboot3handonsdigihaul.dto.shipment;

import java.math.BigDecimal;

public class CreateShipmentRequest {

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
    private String remarks;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

