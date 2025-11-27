package mx.uv.listi.Restaurante.Models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos_detalle")
public class PedidoDetalle {

    @EmbeddedId
    private PedidoDetalleId id = new PedidoDetalleId();

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("platoId")
    @JoinColumn(name = "plato_id")
    private Plato plato;

    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    public PedidoDetalle() {}
    
    // --- Getters y Setters (setPedido actualizado) ---

    public PedidoDetalleId getId() { return id; }
    public void setId(PedidoDetalleId id) { this.id = id; }
    public Pedido getPedido() { return pedido; }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        // El tipo de ID ahora es Long, lo cual es manejado correctamente por id.setPedidoId(Long)
        if (pedido != null && pedido.getId() != null) {
            this.id.setPedidoId(pedido.getId());
        }
    }

    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) {
        this.plato = plato;
        if (plato != null && plato.getId() != null) {
            this.id.setPlatoId(plato.getId());
        }
    }

    // ... (Otros Getters y Setters omitidos por brevedad)
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
}