package com.salesianostriana.FoodDelivery_Manager.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Entrega;
import com.salesianostriana.FoodDelivery_Manager.repository.EntregaRepository;
import com.salesianostriana.FoodDelivery_Manager.service.base.BaseServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService extends BaseServiceImpl<Entrega, Long, EntregaRepository>{

    private final EntregaRepository entregaRepository;

    public Entrega save(Entrega entrega) {
        return entregaRepository.save(entrega);
    }
}
