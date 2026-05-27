package com.salesianostriana.FoodDelivery_Manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Pedido;
import com.salesianostriana.FoodDelivery_Manager.repository.PedidoRepository;
import com.salesianostriana.FoodDelivery_Manager.service.base.BaseServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PedidoService extends BaseServiceImpl<Pedido, Long, PedidoRepository>{

    private final PedidoRepository pedidoRepository;

    @Override
     public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @Override
     public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    @Override
     public void deleteById(Long id){
        pedidoRepository.deleteById(id);
    }






}
