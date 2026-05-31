package com.salesianostriana.FoodDelivery_Manager.exception;

public class TiempoExcedidoException extends RuntimeException {
    public TiempoExcedidoException(String mensaje) {
        super(mensaje);
    }
}