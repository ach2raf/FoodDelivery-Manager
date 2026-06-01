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
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.FoodDelivery_Manager.model.Entrega;
import com.salesianostriana.FoodDelivery_Manager.service.EntregaService;
import com.salesianostriana.FoodDelivery_Manager.service.PedidoService;
import com.salesianostriana.FoodDelivery_Manager.service.RepartidorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/entregas")
@AllArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;
    private final RepartidorService repartidorService;
    private final PedidoService pedidoService;

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

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entregas", entregaService.findAll());
        return "entregasLista";
    }

    @GetMapping("/editarEntrega/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Entrega> entrega = entregaService.findById(id);
        if (entrega.isPresent()) {
            model.addAttribute("entrega", entrega.get());
            model.addAttribute("repartidores", repartidorService.findAll());
            return "entregaFormulario";
        }
        return "redirect:/entregas?error=true";
    }

    @GetMapping("/borrarEntrega/{id}")
    public String borrar(@PathVariable Long id) {
        Optional<Entrega> entrega = entregaService.findById(id);
        if (entrega.isPresent()) {
            entregaService.deleteById(id);
        } else {
            return "redirect:/entregas?error=true";
        }
        return "redirect:/entregas";
    }

    @GetMapping("/asignarPedido/{entregaId}")
    public String mostrarAsignar(@PathVariable Long entregaId, Model model) {
        Optional<Entrega> entrega = entregaService.findById(entregaId);
        if (entrega.isPresent()) {
            model.addAttribute("entrega", entrega.get());
            model.addAttribute("pedidos", pedidoService.findPedidosPendientes());
            return "asignarPedido";
        }
        return "redirect:/entregas?error=true";
    }

    @PostMapping("/asignarPedido/submit")
    public String asignar(@RequestParam Long entregaId,
            @RequestParam Long pedidoId,
            @RequestParam Integer prioridad,
            Model model) {
        try {
            entregaService.asignarPedido(entregaId, pedidoId, prioridad);
        } catch (RuntimeException e) {
            Optional<Entrega> entrega = entregaService.findById(entregaId);
            entrega.ifPresent(en -> model.addAttribute("entrega", en));
            model.addAttribute("pedidos", pedidoService.findPedidosPendientes());
            model.addAttribute("error", e.getMessage());
            return "asignarPedido";
        }
        return "redirect:/entregas";
    }

    @GetMapping("/completarPedido/{entregaPedidoId}")
    public String completarPedido(@PathVariable Long entregaPedidoId) {
        entregaService.marcarComoEntregado(entregaPedidoId);
        return "redirect:/entregas";
    }

}
