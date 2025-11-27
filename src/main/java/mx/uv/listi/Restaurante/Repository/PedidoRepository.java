package mx.uv.listi.Restaurante.Repository;

import mx.uv.listi.Restaurante.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Buscar pedidos por el ID del cliente
    List<Pedido> findByClienteId(Long clienteId);
    
    // Buscar pedidos por estado
    List<Pedido> findByEstado(Pedido.Estado estado);
}