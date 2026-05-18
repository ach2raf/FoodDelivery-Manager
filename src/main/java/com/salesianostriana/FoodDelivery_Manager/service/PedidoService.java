package com.salesianostriana.FoodDelivery_Manager.service;

import java.util.List;

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

     public Pedido findById(Long id){
        return  pedidoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Pedido no encontrado con id: "+id));
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }




}
