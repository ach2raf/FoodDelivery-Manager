package com.salesianostriana.FoodDelivery_Manager.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.service.EntregaService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/consultas")
@AllArgsConstructor
public class ConsultaController {

    private final EntregaService entregaService;

    @GetMapping("/pedidosPorFecha")
    public String pedidosPorFecha(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin,
            Model model) {
        if (inicio != null && fin != null) {
            model.addAttribute("entregas",
                    entregaService.findByFecha(inicio, fin));
        }
        return "consultas/pedidosPorFecha";
    }

    @GetMapping("/repartidoresActivos")
    public String repartidoresActivos(Model model) {
        model.addAttribute("repartidores",
                entregaService.findRepartidoresActivos());
        return "consultas/repartidoresActivos";
    }

    @GetMapping("/entregasRapidas")
    public String entregasRapidas(Model model) {
        model.addAttribute("entregas",
                entregaService.findEntregasRapidas());
        return "consultas/entregasRapidas";
    }

    @GetMapping("/rankingRepartidores")
    public String rankinRepartidors(Model model) {
        model.addAttribute("entregas",
                entregaService.findRankingRepartidores());
        return "consultas/rankingRepartidores";
    }

    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(Model model) {
        Double costeMedio = entregaService.obtenerCosteMedio();
        Map.Entry<Repartidor, Double> topGanador = entregaService.obtenerRepartidorMasIngresos();
        model.addAttribute("costeMedio", costeMedio);
        if (topGanador != null) {
            model.addAttribute("mejorRepartidor", topGanador.getKey()); 
            model.addAttribute("totalIngresos", topGanador.getValue()); 
        }
        return "consultas/estadisticas";
    }
}
