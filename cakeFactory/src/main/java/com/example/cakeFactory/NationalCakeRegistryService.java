package com.example.cakeFactory;

import org.springframework.stereotype.Service;

@Service
public class NationalCakeRegistryService {

    public void registerCakeSale(final String cakeType){
        printResult(cakeType);
    }

    private static void printResult(final String cakeType) {
        System.out.println(cakeType + " registered");
    }

}
