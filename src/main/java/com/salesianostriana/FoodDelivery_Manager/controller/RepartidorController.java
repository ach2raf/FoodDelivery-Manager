package com.salesianostriana.FoodDelivery_Manager.controller;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.service.RepartidorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/repartidores")
public class RepartidorController {

    @Autowired
    private  RepartidorService repartidorService;

   @GetMapping
   public String listaRepartidores(Model model){

    model.addAttribute("repartidores", repartidorService.findAll());
    return "repartidoresLista";
   }

   @GetMapping("/nuevoRepartidor")
   public String nuevo(Model model){

    model.addAttribute("repartidor",new Repartidor());
    return "repartidorFormulario";
   }
   
   @GetMapping("/editarRepartidor/{id}")
   public String editar(@PathVariable Long id,Model model){
    model.addAttribute("repartidor", repartidorService.findById(id));
    return "repartidorFormulario";
   }

   @PostMapping("/guardarRepartidor")
   public String guardar(@Valid @ModelAttribute Repartidor repartidor,BindingResult result){
    if(result.hasErrors()){
        return "repartidorFormulario";
    }
    repartidorService.save(repartidor);
    return "redirect:/repartidores";
   }

   @GetMapping("/borrarRepartidor/{id}")
   public String borrar(@PathVariable Long id){
    repartidorService.deleteById(id);
    return "redirect:/repartidores";
   }
}
