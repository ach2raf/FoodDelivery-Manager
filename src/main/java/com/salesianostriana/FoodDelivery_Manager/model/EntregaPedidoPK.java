package com.salesianostriana.FoodDelivery_Manager.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Embeddable
public class EntregaPedidoPK implements Serializable{

    private static final long serialVersionUID= 1L;

    private Long entrega_id;
    private Long pedido_id;

    public EntregaPedidoPK(Long entrega_id, Long pedido_id) {
        this.entrega_id = entrega_id;
        this.pedido_id = pedido_id;
    }
}
