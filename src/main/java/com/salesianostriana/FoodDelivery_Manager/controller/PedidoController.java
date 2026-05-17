package com.salesianostriana.FoodDelivery_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.FoodDelivery_Manager.model.Pedido;
import com.salesianostriana.FoodDelivery_Manager.service.PedidoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public String listarPedidos(Model model){
        model.addAttribute("pedidos", pedidoService.findAll());
        return "pedidoLista";
    }

    @GetMapping("/nuevoPedido")
    public String nuevo(Model model){

        model.addAttribute("pedido", new Pedido());
        return "pedidoFormulario";
    }

    @GetMapping("/editarPedido/{id}")
    public String editar(@PathVariable Long id, Model model){

        model.addAttribute("pedido", pedidoService.findById(id));
        return "pedidoFormulario";
    }

    @PostMapping("/guardarPedido")
    public String guardar(@Valid @ModelAttribute Pedido pedido, BindingResult result){

        if(result.hasErrors()){
            return "PedidoFormulario";
        }
        pedidoService.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/borrarPedido/{id}")
    public String borrar(@PathVariable Long id){
        pedidoService.deleteById(id);
        return "redirect:/pedidos";
    }
}
