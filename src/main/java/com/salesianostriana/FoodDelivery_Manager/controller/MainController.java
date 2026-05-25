package com.salesianostriana.FoodDelivery_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mostrarIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }
}
