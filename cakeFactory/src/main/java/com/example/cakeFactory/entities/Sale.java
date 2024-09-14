package com.example.cakeFactory.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    // Many sales can be of one cake
    @ManyToOne
    @JoinColumn(name = "cake_id")
    private Cake cake;

    private double price;
    private double finalPriceWithTaxes;
    private LocalDateTime timestamp;

    // Constructors
    public Sale() {}

    public Sale(Cake cake, double price, double finalPriceWithTaxes, LocalDateTime timestamp) {
        this.cake = cake;
        this.price = price;
        this.finalPriceWithTaxes = finalPriceWithTaxes;
        this.timestamp = timestamp;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(final Long saleId) {
        this.saleId = saleId;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(final Cake cake) {
        this.cake = cake;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getFinalPriceWithTaxes() {
        return finalPriceWithTaxes;
    }

    public void setFinalPriceWithTaxes(final double finalPriceWithTaxes) {
        this.finalPriceWithTaxes = finalPriceWithTaxes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", cake=" + cake +
                ", price=" + price +
                ", finalPriceWithTaxes=" + finalPriceWithTaxes +
                ", timestamp=" + timestamp +
                '}';
    }
}
