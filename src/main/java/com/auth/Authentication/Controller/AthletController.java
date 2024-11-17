package com.auth.Authentication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AthletController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Hello from Thymeleaf!");
        return "home";  // This will resolve to src/main/resources/templates/home.html
    }
}
