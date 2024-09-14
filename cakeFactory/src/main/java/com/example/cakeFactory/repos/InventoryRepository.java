package com.example.cakeFactory.repos;

import com.example.cakeFactory.entities.Cake;
import com.example.cakeFactory.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByCake(Cake cake);
}
