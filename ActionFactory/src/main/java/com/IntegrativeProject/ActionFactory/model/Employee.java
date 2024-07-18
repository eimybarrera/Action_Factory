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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @ManyToOne()
    @JoinColumn(name = "id", nullable = false)//el nulable significa que esta columna no puede ser nula
    private Role role;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "last_acces")
    private LocalDateTime lastAcces;

    @Column(name = "status")
    private  String status;

    public Employee() {
    }

    public Employee(Long id, String name, String email, String password, Role role, LocalDate hireDate, LocalDateTime lastAcces, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.hireDate = hireDate;
        this.lastAcces = lastAcces;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateTime getLastAcces() {
        return lastAcces;
    }

    public void setLastAcces(LocalDateTime lastAcces) {
        this.lastAcces = lastAcces;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

