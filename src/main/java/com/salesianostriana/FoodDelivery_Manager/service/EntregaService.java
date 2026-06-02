package com.salesianostriana.FoodDelivery_Manager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.FoodDelivery_Manager.exception.EntregaInvalidaException;
import com.salesianostriana.FoodDelivery_Manager.exception.RepartidorNoDisponibleException;
import com.salesianostriana.FoodDelivery_Manager.exception.TiempoExcedidoException;
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

    @Override
    public Entrega save(Entrega entrega) {

        if (entrega.getRepartidor() == null || entrega.getRepartidor().getId() == null) {
            throw new RepartidorNoDisponibleException("La entrega no tiene repartidor");
        }

        if (entrega.getTiempo() != null && entrega.getTiempo() > 120) {
            throw new TiempoExcedidoException(
                    "El tiempo no puede superar los 120 minutos");
        }

        if (tieneSolapamiento(entrega)) {
            throw new EntregaInvalidaException(
                    "El repartidor ya tiene una entrega en ese horario");
        }

        return entregaRepository.save(entrega);
    }

    @Override
    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    @Override
    public Optional<Entrega> findById(Long id) {
        return entregaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        entregaRepository.deleteById(id);
    }

    public void asignarPedido(Long entregaId, Long pedidoId, Integer prioridad) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        validarLimitePedidos(entrega);
        validarPedidoDuplicado(entrega, pedidoId);

        EntregaPedido ep = new EntregaPedido();
        ep.setEntrega(entrega);
        ep.setPedido(pedido);
        ep.setPrioridad(prioridad);
        ep.setEstado(EstadoPedido.EN_REPARTO);
        ep.setCoste(calcularCoste(pedido, entrega.getRepartidor()));

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

    private boolean tieneSolapamiento(Entrega nueva) {
        if (nueva.getRepartidor() == null || nueva.getFecha() == null
                || nueva.getTiempo() == null)
            return false;

        LocalDateTime inicioNueva = nueva.getFecha();
        LocalDateTime finNueva = nueva.getFecha().plusMinutes(nueva.getTiempo());

        return entregaRepository.findAll().stream()
                .filter(e -> e.getRepartidor() != null)
                .filter(e -> e.getRepartidor().getId()
                        .equals(nueva.getRepartidor().getId()))
                .filter(e -> nueva.getId() == null || !e.getId().equals(nueva.getId()))
                .anyMatch(e -> {
                    LocalDateTime ini = e.getFecha();
                    LocalDateTime fin = e.getFecha().plusMinutes(e.getTiempo());
                    return inicioNueva.isBefore(fin) && finNueva.isAfter(ini);
                });
    }

    private Double calcularCoste(Pedido pedido, Repartidor repartidor) {
        double factor = switch (repartidor.getZona().toLowerCase()) {
            case "centro" -> 1.0;
            case "norte", "sur" -> 1.2;
            case "este", "oeste" -> 1.5;
            default -> 1.3;
        };
        return pedido.getPrecio() * factor;
    }

    private void validarLimitePedidos(Entrega entrega) {
        if (entrega.getEntregaPedidos() != null &&
                entrega.getEntregaPedidos().size() >= 5) {
            throw new EntregaInvalidaException(
                    "Una entrega no puede tener más de 5 pedidos");
        }
    }

    private void validarPedidoDuplicado(Entrega entrega, Long pedidoId) {
        boolean yaAsignado = entrega.getEntregaPedidos() != null &&
                entrega.getEntregaPedidos().stream()
                        .anyMatch(ep -> ep.getPedido().getId().equals(pedidoId));
        if (yaAsignado) {
            throw new EntregaInvalidaException(
                    "Este pedido ya está asignado a esta entrega");
        }
    }

<<<<<<< Updated upstream
=======
    public void marcarComoEntregado(Long entregaPedidoId) {
        EntregaPedido ep = entregaPedidoRepository.findById(entregaPedidoId)
                .orElseThrow(() -> new RuntimeException("Asignación de pedido no encontrada"));

        ep.setEstado(EstadoPedido.ENTREGADO);
        entregaPedidoRepository.save(ep);
    }

>>>>>>> Stashed changes
}
