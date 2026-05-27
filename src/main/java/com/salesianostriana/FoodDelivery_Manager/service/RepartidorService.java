package com.salesianostriana.FoodDelivery_Manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.repository.RepartidorRepository;
import com.salesianostriana.FoodDelivery_Manager.service.base.BaseServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RepartidorService extends BaseServiceImpl<Repartidor, Long, RepartidorRepository>{

        private final RepartidorRepository repartidorRepository;

        @Override
         public Repartidor save(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

        @Override
    public Optional<Repartidor> findById(Long id) {
        return repartidorRepository.findById(id);
    }


       

        @Override
   public List<Repartidor> findAll() {
        return repartidorRepository.findAll();
    }
 
        @Override
    public void deleteById(Long id) {
        repartidorRepository.deleteById(id);
    }



}
