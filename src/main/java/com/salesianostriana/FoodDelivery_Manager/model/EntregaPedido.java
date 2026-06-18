package com.salesianostriana.FoodDelivery_Manager.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregaPedido {

    @EmbeddedId
    private EntregaPedidoPK entregaPedidoPK = new EntregaPedidoPK();

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    private Double coste;

    private Integer prioridad;

    @ManyToOne
    @MapsId("entrega_id")
    @JoinColumn(name = "entrega_id", insertable = false, updatable = false)
    private Entrega entrega;

    @ManyToOne
    @MapsId("pedido_id")
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    public void addToEntrega(Entrega e) {
        e.getEntregaPedidos().add(this);
        this.entrega = e;
    }

    public void removeFromEntrega(Entrega e) {
        e.getEntregaPedidos().remove(this);
        this.entrega = null;
    }
}
