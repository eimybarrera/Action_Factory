package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @Column(name = "imei", unique = true)
    private Long imei;

    @Column(name = "status")
    private String status;

    @Column(name = "score")
    private int score;

    @Column(name = "validation_status")
    private String validationStatus;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public Device(){
    }

    public Device(Long imei, String status, int score, String validationStatus, LocalDate validationDate, Supplier supplier, Employee employee) {
        this.imei = imei;
        this.status = status;
        this.score = score;
        this.validationStatus = validationStatus;
        this.validationDate = validationDate;
        this.supplier = supplier;
        this.employee = employee;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
