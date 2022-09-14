package com.bintou.mediscreen.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public  String login(Model model) {
        return "login";
    }

    @RequestMapping("/app-logout")
    public String logOut(Model model) {
        return "redirect:/login";
    }

}

