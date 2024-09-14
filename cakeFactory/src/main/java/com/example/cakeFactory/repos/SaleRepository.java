package com.example.cakeFactory.repos;

import com.example.cakeFactory.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
