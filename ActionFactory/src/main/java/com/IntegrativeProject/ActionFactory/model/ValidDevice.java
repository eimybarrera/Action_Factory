package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "valid_device")
public class ValidDevice {

    @Id
    @Column(name = "validation_id")
    private Long validationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="imei")
    private Device device;

    public ValidDevice(){
    }

    public ValidDevice(Device device) {
        this.device= device;
    }

    public Long getValidationId() {
        return validationId;
    }

    public void setValidationId(Long validationId) {
        this.validationId = validationId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
