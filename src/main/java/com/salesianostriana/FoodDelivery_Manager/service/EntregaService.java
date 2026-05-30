package com.salesianostriana.FoodDelivery_Manager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.model.Entrega;
import com.salesianostriana.FoodDelivery_Manager.model.EntregaPedido;
import com.salesianostriana.FoodDelivery_Manager.model.EstadoPedido;
import com.salesianostriana.FoodDelivery_Manager.model.Pedido;
import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.repository.EntregaPedidoRepository;
import com.salesianostriana.FoodDelivery_Manager.repository.EntregaRepository;
import com.salesianostriana.FoodDelivery_Manager.repository.PedidoRepository;
import com.salesianostriana.FoodDelivery_Manager.service.base.BaseServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService extends BaseServiceImpl<Entrega, Long, EntregaRepository> {

    private final EntregaRepository entregaRepository;
    private final PedidoRepository pedidoRepository;
    private final EntregaPedidoRepository entregaPedidoRepository;

    public Entrega save(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> findById(Long id) {
        return entregaRepository.findById(id);
    }

    public void deleteById(Long id) {
        entregaRepository.deleteById(id);
    }

    public void asignarPedido(Long entregaId, Long pedidoId, Integer prioridad) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        EntregaPedido ep = new EntregaPedido();
        ep.setEntrega(entrega);
        ep.setPedido(pedido);
        ep.setPrioridad(prioridad);
        ep.setEstado(EstadoPedido.EN_REPARTO);
        ep.setCoste(pedido.getPrecio() * 1.2);

        entregaPedidoRepository.save(ep);
    }

    public List<Entrega> findByFecha(LocalDateTime inicio, LocalDateTime fin) {
        return entregaRepository.findByFechaBetween(inicio, fin);
    }

    public List<Repartidor> findRepartidoresActivos() {
        return entregaRepository.findRepartidoresActivos();
    }

    public List<Entrega> findEntregasRapidas() {
        return entregaRepository.findByTiempoLessThan(30);
    }

}
