package com.IntegrativeProject.ActionFactory.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private  Long id;

    @Column(name = "name")
    private  String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
