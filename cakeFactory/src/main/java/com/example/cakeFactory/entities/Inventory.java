package com.example.cakeFactory.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One-to-one relationship with Cake
    @OneToOne
    @JoinColumn(name = "cake_id")
    private Cake cake;

    private int quantityAvailable;

    // Constructors
    public Inventory() {}

    public Inventory(Cake cake, int quantityAvailable) {
        this.cake = cake;
        this.quantityAvailable = quantityAvailable;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(final Cake cake) {
        this.cake = cake;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(final int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", cake=" + cake +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
