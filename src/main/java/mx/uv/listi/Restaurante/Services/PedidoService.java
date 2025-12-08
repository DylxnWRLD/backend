package mx.uv.listi.Restaurante.Services;

import mx.uv.listi.Restaurante.DTOs.PedidoRequest;
import mx.uv.listi.Restaurante.Models.*;
import mx.uv.listi.Restaurante.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Pedido crearPedido(PedidoRequest request) {
        // 1. Validar Usuario
        Usuario cliente = usuarioRepository.findById(request.usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Crear la entidad Pedido (Cabecera)
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado(Pedido.Estado.PENDIENTE);
        
        // 3. Procesar los detalles
        List<PedidoDetalle> detalles = new ArrayList<>();
        double totalCalculado = 0.0;

        for (PedidoRequest.DetalleRequest item : request.productos) {
            Plato plato = platoRepository.findById(item.platoId)
                    .orElseThrow(() -> new RuntimeException("Plato no encontrado: " + item.platoId));

            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setPedido(pedido); // Vinculación importante para la clave compuesta
            detalle.setPlato(plato);
            detalle.setCantidad(item.cantidad);
            detalle.setPrecioUnitario(plato.getPrecioBase()); // Usamos precio actual de la DB

            totalCalculado += plato.getPrecioBase() * item.cantidad;
            detalles.add(detalle);
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(totalCalculado);

        // 4. Guardar (CascadeType.ALL guardará los detalles automáticamente)
        return pedidoRepository.save(pedido);
    }
    
    // Método para listar pedidos de un usuario
    public List<Pedido> obtenerPedidosPorUsuario(Long usuarioId) {
        return pedidoRepository.findByClienteId(usuarioId);
    }
}