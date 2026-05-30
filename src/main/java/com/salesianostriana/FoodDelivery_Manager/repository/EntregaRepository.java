package com.salesianostriana.FoodDelivery_Manager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesianostriana.FoodDelivery_Manager.model.Entrega;
import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    List<Entrega> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT DISTINCT ep.entrega.repartidor FROM EntregaPedido ep " +
            "WHERE ep.estado = 'EN_REPARTO'")
    List<Repartidor> findRepartidoresActivos();

    List<Entrega> findByTiempoLessThan(Integer minutos);
}
