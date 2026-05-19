package com.salesianostriana.FoodDelivery_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.service.RepartidorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/repartidores")
@AllArgsConstructor
public class RepartidorController {

    
    private  final RepartidorService repartidorService;

     @GetMapping("/nuevoRepartidor")
   public String nuevo(Model model){


    model.addAttribute("repartidor",new Repartidor());
    return "repartidorFormulario";
   }

    @PostMapping("/nuevoRepartidor/submit")
   public String guardar(@Valid @ModelAttribute Repartidor repartidor,BindingResult result){
    if(result.hasErrors()){
        return "repartidorFormulario";
    }
    repartidorService.save(repartidor);
    return "redirect:/repartidores";
   }

}
