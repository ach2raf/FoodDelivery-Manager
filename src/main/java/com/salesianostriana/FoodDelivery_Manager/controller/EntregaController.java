package com.salesianostriana.FoodDelivery_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.FoodDelivery_Manager.model.Entrega;
import com.salesianostriana.FoodDelivery_Manager.service.EntregaService;
import com.salesianostriana.FoodDelivery_Manager.service.RepartidorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/entregas")
@AllArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;
    private final RepartidorService repartidorService;

    @GetMapping("/nuevaEntrega")
    public String nuevo(Model model) {
        model.addAttribute("entrega", new Entrega());
        model.addAttribute("repartidores", repartidorService.findAll());
        return "entregaFormulario";
    }

    @PostMapping("/nuevaEntrega/submit")
    public String guardar(@Valid @ModelAttribute Entrega entrega,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("repartidores", repartidorService.findAll());
            return "entregaFormulario";
        }
        entregaService.save(entrega);
        return "redirect:/entregas";
    }

}
