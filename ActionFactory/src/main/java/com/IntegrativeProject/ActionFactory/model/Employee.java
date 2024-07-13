package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "last_acces")
    private LocalDateTime lastAcces;

    @Column(name = "status")
    private  String status;

    public Employee() {
    }

    public Employee(int id, String name, String email, String password, Role role, LocalDate hireDate, LocalDateTime lastAcces, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.hireDate = hireDate;
        this.lastAcces = lastAcces;
        this.status = status;
    }
}

