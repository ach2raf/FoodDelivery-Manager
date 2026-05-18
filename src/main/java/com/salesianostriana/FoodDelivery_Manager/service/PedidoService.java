package com.salesianostriana.FoodDelivery_Manager.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Pedido;
import com.salesianostriana.FoodDelivery_Manager.repository.PedidoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PedidoService {

    private final PedidoRepository pedidoRepository;

     public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

}
