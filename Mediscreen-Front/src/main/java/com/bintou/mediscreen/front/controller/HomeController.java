package com.bintou.mediscreen.front.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Authentication authentication, Model model) {
        String name = authentication.getName();
        model.addAttribute("name", "vous êtes connecté en tant que : " + name);
        return "home";
    }

    @RequestMapping("/admin/home")
    public String adminHome(Model model)
    {
        return "redirect:/api/patients";
    }

}

