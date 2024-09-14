package com.example.cakeFactory;

import com.example.cakeFactory.entities.Cake;
import com.example.cakeFactory.entities.Inventory;
import com.example.cakeFactory.entities.Sale;
import com.example.cakeFactory.repos.InventoryRepository;
import com.example.cakeFactory.repos.SaleRepository;
import com.example.cakeFactory.services.CakeListing;
import com.example.cakeFactory.services.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CakeController {

    @Autowired
    CakeService cakeService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/")
    public String showCakes(Model model) {
        List<CakeListing> cakeListings = cakeService.getAllCakesListings();
        model.addAttribute("cakeListings", cakeListings);
        return "cakes";
    }

    @PostMapping("/buyCake")
    public String buyCake(@RequestParam("cakeId") long cakeId, Model model) {
        cakeService.sellCake(cakeId);
        // Add the selected cake to the model to display a thank you message
        Cake cake = cakeService.getCakeById(cakeId);
        model.addAttribute("selectedCake", cake.getFlavour());
        return "thankyou";  // Redirect to the thank you page after purchase
    }

    @GetMapping("/sales")
    public String showInventory(Model model) {
        List<Inventory> inventories = inventoryRepository.findAll();
        model.addAttribute("inventories", inventories);
        return "inventory";  // Thymeleaf template for inventory display
    }
}