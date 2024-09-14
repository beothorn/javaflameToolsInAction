package com.example.cakeFactory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flavour;
    private String image;
    private double price;

    // Constructors
    public Cake() {}

    public Cake(String flavour, String image, double price) {
        this.flavour = flavour;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id=" + id +
                ", flavour='" + flavour + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
