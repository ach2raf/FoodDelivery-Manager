package com.salesianostriana.FoodDelivery_Manager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(EntregaInvalidaException.class)
    public String handleEntregaInvalida(EntregaInvalidaException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

    @ExceptionHandler(TiempoExcedidoException.class)
    public String handleTiempoExcedido(TiempoExcedidoException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

    @ExceptionHandler(RepartidorNoDisponibleException.class)
    public String handleRepartidorNoDisponible(
            RepartidorNoDisponibleException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
