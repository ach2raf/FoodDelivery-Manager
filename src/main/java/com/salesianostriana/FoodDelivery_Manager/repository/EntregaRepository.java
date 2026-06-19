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

    @Query("SELECT e FROM Entrega e WHERE e.repartidor.id = :repartidorId " +
            "AND (:entregaId IS NULL OR e.id <> :entregaId) " +
            "AND e.fecha < :finNueva " +
            "AND FUNCTION('TIMESTAMPADD', MINUTE, e.tiempo, e.fecha) > :inicioNueva")
    List<Entrega> findSolapamientos(Long repartidorId, Long entregaId,
            LocalDateTime inicioNueva, LocalDateTime finNueva);

    @Query("SELECT e.repartidor, COUNT(e) as totalEntregas FROM Entrega e " +
            "WHERE e.repartidor IS NOT NULL " +
            "GROUP BY e.repartidor " +
            "ORDER BY COUNT(e) DESC")
    List<Object[]> findRepartidoresOrdenadosPorEntregas();
}
