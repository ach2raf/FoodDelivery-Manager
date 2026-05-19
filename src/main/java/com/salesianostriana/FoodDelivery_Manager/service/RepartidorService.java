package com.salesianostriana.FoodDelivery_Manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.repository.RepartidorRepository;
import com.salesianostriana.FoodDelivery_Manager.service.base.BaseServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RepartidorService extends BaseServiceImpl<Repartidor, Long, RepartidorRepository>{

        private final RepartidorRepository repartidorRepository;

         public Repartidor save(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

    /*  public Repartidor findById(Long id) {
        return repartidorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Repartidor no encontrado con id: " + id));
    }*/

       

   public List<Repartidor> findAll() {
        return repartidorRepository.findAll();
    }
 


}
