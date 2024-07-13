package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "InvalidDevice")
public class InvalidDevice {
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

    public InvalidDevice() {
    }

    public InvalidDevice(Long imei, String status, Supplier supplier, String score, String validationStatus, LocalDate validationDate) {
        this.imei = imei;
        this.status = status;
        this.supplier = supplier;
        this.score = score;
        this.validationStatus = validationStatus;
        this.validationDate = validationDate;
    }

}
