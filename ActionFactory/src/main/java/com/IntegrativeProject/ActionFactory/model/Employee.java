package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Column(name = "status")
    private  String status;

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = true)//el nullable significa que esta columna no puede ser nula
    private Role role;

    public Employee() {
    }

    public Employee(Long id) {
    }

    public Employee(Long id, String name, String email, String password, LocalDate hireDate, LocalDateTime lastAccess, String status, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.lastAccess = lastAccess;
        this.status = status;
        this.role = role;
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

