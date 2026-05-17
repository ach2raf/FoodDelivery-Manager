package com.salesianostriana.FoodDelivery_Manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;
    @Positive(message = "El precio debe ser positivo")
    private double precio;
    @NotBlank(message = "El cliente es obligatorio")
    private String cliente ;
    @NotBlank(message = "El contenido es obligatorio")
    private String contenido; 

    @ManyToOne
    @JoinColumn(name = "entrega_pedido_id")
    private EntregaPedido entregaPedido;



}
