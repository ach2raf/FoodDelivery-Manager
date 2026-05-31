package com.salesianostriana.FoodDelivery_Manager.exception;

public class EntregaInvalidaException extends RuntimeException {
    public EntregaInvalidaException(String mensaje) {
        super(mensaje);
    }
}