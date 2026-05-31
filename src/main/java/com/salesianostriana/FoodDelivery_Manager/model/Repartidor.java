package com.salesianostriana.FoodDelivery_Manager.model;

import java.util.List;

import com.salesianostriana.FoodDelivery_Manager.usuario.User;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Repartidor extends User{


   


    @NotBlank(message="el nombre es obligatorio")
    private String nombre;
    @NotBlank(message="la zona es obligatoria")
    private String zona;

    @OneToMany(mappedBy = "repartidor")
    private List<Entrega> entregas;

}
