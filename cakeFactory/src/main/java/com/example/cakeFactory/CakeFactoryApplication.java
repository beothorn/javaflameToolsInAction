package com.example.cakeFactory;

import com.example.cakeFactory.entities.Cake;
import com.example.cakeFactory.entities.Inventory;
import com.example.cakeFactory.repos.CakeRepository;
import com.example.cakeFactory.repos.InventoryRepository;
import com.example.cakeFactory.repos.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CakeFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeFactoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(
			CakeRepository cakeRepository,
			InventoryRepository inventoryRepository,
			SaleRepository saleRepository
	) {
		return args -> {
			saleRepository.deleteAll();
			inventoryRepository.deleteAll();
			cakeRepository.deleteAll();

			Cake chocolateCake = new Cake("Chocolate Cake", "chocolate.jpg", 5.00);
			Cake whiteCake = new Cake("White Cake", "white.jpg", 6.00);
			Cake strawberryCake = new Cake("Strawberry Cake", "strawberry.jpg", 5.30);

			cakeRepository.save(chocolateCake);
			cakeRepository.save(whiteCake);
			cakeRepository.save(strawberryCake);

			inventoryRepository.save(new Inventory(chocolateCake, 10));  // 10 units available
			inventoryRepository.save(new Inventory(whiteCake, 2));
			inventoryRepository.save(new Inventory(strawberryCake, 12));
		};
	}

}
