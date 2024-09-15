package com.example.cakeFactory;

import com.example.cakeFactory.entities.Sale;
import com.example.cakeFactory.repos.SaleRepository;
import com.example.cakeFactory.services.CakeListing;
import com.example.cakeFactory.services.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CakeController {

    @Autowired
    CakeService cakeService;

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
        Sale sale = cakeService.sellCake(cakeId);
        model.addAttribute("sale", sale);
        return "thankyou";
    }

    @GetMapping("/sales")
    public String showSales(Model model) {
        List<Sale> allSales = cakeService.getSales();
        model.addAttribute("allSales", allSales);
        return "sales";
    }
}