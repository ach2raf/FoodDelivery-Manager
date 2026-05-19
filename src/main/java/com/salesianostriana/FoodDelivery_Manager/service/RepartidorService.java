package com.salesianostriana.FoodDelivery_Manager.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.repository.RepartidorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RepartidorService {

        private final RepartidorRepository repartidorRepository;

         public Repartidor save(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

}
