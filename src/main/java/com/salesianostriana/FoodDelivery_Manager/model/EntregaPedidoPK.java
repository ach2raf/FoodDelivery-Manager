package com.salesianostriana.FoodDelivery_Manager.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EntregaPedidoPK implements Serializable{

    private static final long serialVersionUID= 1L;

    private Long entrega_id;
    private Long pedido_id;

}
