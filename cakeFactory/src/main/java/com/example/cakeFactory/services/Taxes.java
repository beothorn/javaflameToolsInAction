package com.example.cakeFactory.services;

import org.springframework.stereotype.Service;

@Service
public class Taxes {

    public double priceWithTaxes(double price) {
        double taxRate = 0.10;
        return price * (1 + taxRate);
    }

}
