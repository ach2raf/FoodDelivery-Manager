package com.salesianostriana.FoodDelivery_Manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.repository.RepartidorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RepartidorService {

    private final RepartidorRepository repartidorRepository;

    

   public List<Repartidor> findAll() {
        return repartidorRepository.findAll();
    }

    public Repartidor findById(Long id) {
        return repartidorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Repartidor no encontrado con id: " + id));
    }

    public Repartidor save(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

    public void deleteById(Long id) {
        repartidorRepository.deleteById(id);
    }
}
