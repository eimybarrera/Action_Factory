package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "validDevice")
public class ValidDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imei")
    private Long imei;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "score")
    private  String score;

    @Column(name = "validation_status")
    private String validationStatus;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    public ValidDevice() {
    }

    public ValidDevice(Long imei, String status, Supplier supplier, String score, String validationStatus, LocalDate validationDate) {
        this.imei = imei;
        this.status = status;
        this.supplier = supplier;
        this.score = score;
        this.validationStatus = validationStatus;
        this.validationDate = validationDate;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDate validationDate) {
        this.validationDate = validationDate;
    }
}
