package com.example.cakeFactory.services;

import com.example.cakeFactory.entities.Cake;

public class CakeListing {

    private Cake cake;

    private int quantityAvailable;

    public CakeListing(final Cake cake, final int quantityAvailable) {
        this.cake = cake;
        this.quantityAvailable = quantityAvailable;
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
        return "CakeListing{" +
                "cake=" + cake +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
