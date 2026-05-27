package com.salesianostriana.FoodDelivery_Manager.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;

    @Positive(message = "El tiempo debe ser positivo")
    private Integer tiempo;

    @ManyToOne
    @JoinColumn(name = "repartidor_id")
    private Repartidor repartidor;

   @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<EntregaPedido> entregaPedidos;
}
