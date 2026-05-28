package com.salesianostriana.FoodDelivery_Manager.service;

import java.util.List;
import java.util.Optional;

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

    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> findById(Long id) {
        return entregaRepository.findById(id);
    }
}
