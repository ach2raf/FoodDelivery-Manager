package com.salesianostriana.FoodDelivery_Manager.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repartidor {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message="el nombre es obligatorio")
    private String nombre;
    @NotBlank(message="la zona es obligatoria")
    private String zona;

    @OneToMany(mappedBy = "repartidor")
    private List<Entrega> entregas;

}
