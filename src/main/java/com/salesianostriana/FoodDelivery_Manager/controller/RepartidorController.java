package com.salesianostriana.FoodDelivery_Manager.controller;

import java.util.Optional;

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

   @GetMapping("/editarRepartidor/{id}")
public String editar(@PathVariable Long id, Model model) {

    Optional<Repartidor>repartidor=repartidorService.findById(id);

    if (repartidor.isPresent()) {
        model.addAttribute("repartidor", repartidor.get());
        return "repartidorFormulario";
    } else {
        return "redirect:/repartidores";
    }
}

@GetMapping
   public String listaRepartidores(Model model){


    model.addAttribute("repartidores", repartidorService.findAll());
    return "repartidoresLista";
   }

   @GetMapping("/borrarRepartidor/{id}")
public String borrar(@PathVariable Long id) {

    Optional<Repartidor> repartidor = repartidorService.findById(id);

    if (repartidor.isPresent()) {
        repartidorService.deleteById(id);
    } else {
        return "redirect:/repartidores?error=true";
    }

    return "redirect:/repartidores";
}

 @GetMapping("/verRepartidor/{id}")
public String ver(@PathVariable Long id, Model model) {

    Optional<Repartidor> repartidor = repartidorService.findById(id);

    if (repartidor.isPresent()) {
        model.addAttribute("repartidor", repartidor.get());
        return "verRepartidor";
    } else {
        return "redirect:/repartidores?error=true";
    }
}


}
