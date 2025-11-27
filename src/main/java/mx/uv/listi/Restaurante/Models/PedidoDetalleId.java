package mx.uv.listi.Restaurante.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta para la entidad PedidoDetalle.
 * Se utiliza Long para 'pedidoId' para ser compatible con el ID de la tabla 'pedidos' (BIGINT).
 */
@Embeddable
public class PedidoDetalleId implements Serializable {

    @Column(name = "pedido_id")
    private Long pedidoId; 

    @Column(name = "plato_id")
    private Long platoId; 

    // Constructor vacío
    public PedidoDetalleId() {}

    // Constructor con campos (SIN ASTERISCOS)
    public PedidoDetalleId(Long pedidoId, Long platoId) { 
        this.pedidoId = pedidoId;
        this.platoId = platoId;
    }

    // --- Getters y Setters ---

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getPlatoId() {
        return platoId;
    }

    public void setPlatoId(Long platoId) {
        this.platoId = platoId;
    }

    // --- Sobrescritura de equals y hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDetalleId that = (PedidoDetalleId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
               Objects.equals(platoId, that.platoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, platoId);
    }
}