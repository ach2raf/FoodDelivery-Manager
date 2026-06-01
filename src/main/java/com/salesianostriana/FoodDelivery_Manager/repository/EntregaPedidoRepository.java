package com.salesianostriana.FoodDelivery_Manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesianostriana.FoodDelivery_Manager.model.EntregaPedido;
import com.salesianostriana.FoodDelivery_Manager.model.Pedido;


@Repository
public interface EntregaPedidoRepository extends JpaRepository<EntregaPedido, Long>{

    @Query("SELECT ep.pedido FROM EntregaPedido ep")
    List<Pedido> findPedidosAsignados();
}
