package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String emali;
    private String webSide;
    private String industrySector;
    private LocalDate joiningDate;
}
