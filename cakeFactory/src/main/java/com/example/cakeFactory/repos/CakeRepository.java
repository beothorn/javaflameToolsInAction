package com.example.cakeFactory.repos;

import com.example.cakeFactory.entities.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Long> {
}
