package com.heliton.controller;

// Java //
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    String index() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
      return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:login";
    }
}