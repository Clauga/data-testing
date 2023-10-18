package com.wcs.datatesting.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fireman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "fireman", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Fire> fires = new HashSet<>();

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

    public Set<Fire> getFires() {
        return fires;
    }

    public void setFires(Set<Fire> fires) {
        this.fires = fires;
    }

    public void addFire(Fire fire) {
        if (fires == null) {
            fires = new HashSet<>();
        }
        fires.add(fire);
        fire.setFireman(this);
    }
}

