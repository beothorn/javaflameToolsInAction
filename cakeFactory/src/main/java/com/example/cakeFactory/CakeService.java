package com.example.cakeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    public List<Cake> getAllCakes() {
        return cakeRepository.findAll();
    }

    public Cake addCake(Cake cake) {
        return cakeRepository.save(cake);
    }

    public Cake getCakeById(Long id) {
        return cakeRepository.findById(id).orElse(null);
    }

    public void deleteCake(Long id) {
        cakeRepository.deleteById(id);
    }
}
