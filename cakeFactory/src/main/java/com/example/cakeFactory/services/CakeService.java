package com.example.cakeFactory.services;

import com.example.cakeFactory.NationalCakeRegistryService;
import com.example.cakeFactory.entities.Cake;
import com.example.cakeFactory.entities.Inventory;
import com.example.cakeFactory.entities.Sale;
import com.example.cakeFactory.repos.CakeRepository;
import com.example.cakeFactory.repos.InventoryRepository;
import com.example.cakeFactory.repos.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private NationalCakeRegistryService nationalCakeRegistryService;

    public void sellCake(final long cakeId) {
        Cake selectedCake = cakeRepository.findById(cakeId).orElseThrow();
        Inventory inventory = inventoryRepository.findByCake(selectedCake);

        if (inventory != null && inventory.getQuantityAvailable() > 0) {
            // Update inventory
            inventory.setQuantityAvailable(inventory.getQuantityAvailable() - 1);
            inventoryRepository.save(inventory);

            // Calculate final price with taxes (e.g., 10% tax)
            double taxRate = 0.10;
            double finalPrice = selectedCake.getPrice() * (1 + taxRate);

            // Record the sale
            Sale sale = new Sale(
                    selectedCake,
                    selectedCake.getPrice(),
                    finalPrice,
                    LocalDateTime.now()
            );
            saleRepository.save(sale);
        }
    }

    public List<CakeListing> getAllCakesListings() {
        List<Cake> cakes = cakeRepository.findAll();
        return (List<CakeListing>) cakes.stream().map(c -> {
            Inventory inventory = inventoryRepository.findByCake(c);
            int quantityAvailable = inventory.getQuantityAvailable();
            if (quantityAvailable <= 0) {
                return Optional.empty();
            }
            return Optional.of(new CakeListing(c, quantityAvailable));
        }).filter(Optional::isPresent).map(Optional::get).toList();
    }

    public Cake getCakeById(Long id) {
        return cakeRepository.findById(id).orElse(null);
    }
}
