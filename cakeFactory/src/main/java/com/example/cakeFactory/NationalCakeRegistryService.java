package com.example.cakeFactory;

import org.springframework.stereotype.Service;

@Service
public class NationalCakeRegistryService {

    public void registerCakeSale(final long cakeId){
        printResult(cakeId);
    }

    private static void printResult(final long cakeId) {
        System.out.println(cakeId + " registered");
    }

}
