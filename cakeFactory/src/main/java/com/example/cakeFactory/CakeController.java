package com.example.cakeFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CakeController {

    @GetMapping("/")
    public String showCakes() {
        return "cakes";  // Render the Thymeleaf page 'cakes.html'
    }

    @PostMapping("/buyCake")
    public String buyCake(@RequestParam("cake") String cake, Model model) {
        // Add the selected cake to the model to display a thank you message
        model.addAttribute("selectedCake", cake);
        return "thankyou";  // Redirect to the thank you page after purchase
    }
}