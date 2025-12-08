package mx.uv.listi.Restaurante.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Representa el detalle de un pedido en la base de datos.
 * 
 * Cada {@code PedidoDetalle} une un {@link Pedido} con un {@link Plato}
 * utilizando una llave compuesta definida en {@link PedidoDetalleId}.
 * También almacena la cantidad solicitada del plato y su precio unitario.
 * 
 * La relación es:
 * <ul>
 *   <li>Muchos detalles pertenecen a un pedido</li>
 *   <li>Muchos detalles pueden referenciar un mismo plato</li>
 * </ul>
 */
@Entity
@Table(name = "pedidos_detalle")
public class PedidoDetalle {

    /**
     * Llave primaria compuesta que contiene los IDs del pedido y el plato.
     */
    @EmbeddedId
    private PedidoDetalleId id = new PedidoDetalleId();

    /**
     * Pedido al que pertenece este detalle.
     * 
     * Se utiliza {@link MapsId} para sincronizar el ID del pedido con el de la clave compuesta.
     */
    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    /**
     * Plato asociado a este detalle del pedido.
     * También forma parte de la clave primaria compuesta.
     */
    @ManyToOne
    @MapsId("platoId")
    @JoinColumn(name = "plato_id")
    private Plato plato;

    /**
     * Cantidad solicitada del plato.
     */
    @Column(nullable = false)
    private Integer cantidad;

    /**
     * Precio unitario del plato al momento del pedido.
     */
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    /**
     * Constructor vacío requerido por JPA.
     */
    public PedidoDetalle() {}

    // -------- Getters y Setters --------

    /**
     * Obtiene la llave compuesta.
     * @return id del detalle (pedidoId + platoId)
     */
    public PedidoDetalleId getId() { return id; }

    /**
     * Establece la llave compuesta.
     * @param id nueva llave compuesta
     */
    public void setId(PedidoDetalleId id) { this.id = id; }

    /**
     * Obtiene el pedido asociado a este detalle.
     * @return pedido
     */
    public Pedido getPedido() { return pedido; }

    /**
     * Establece el pedido asociado y actualiza la clave compuesta.
     * @param pedido pedido al que pertenece este detalle
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        if (pedido != null && pedido.getId() != null) {
            this.id.setPedidoId(pedido.getId());
        }
    }

    /**
     * Obtiene el plato asociado.
     * @return plato del detalle
     */
    public Plato getPlato() { return plato; }

    /**
     * Establece el plato asociado y sincroniza el ID en la clave compuesta.
     * @param plato plato seleccionado
     */
    public void setPlato(Plato plato) {
        this.plato = plato;
        if (plato != null && plato.getId() != null) {
            this.id.setPlatoId(plato.getId());
        }
    }

    /**
     * Obtiene la cantidad solicitada.
     * @return cantidad
     */
    public Integer getCantidad() { return cantidad; }

    /**
     * Establece la cantidad de platos solicitados.
     * @param cantidad cantidad solicitada
     */
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    /**
     * Obtiene el precio unitario del plato.
     * @return precio
     */
    public Double getPrecioUnitario() { return precioUnitario; }

    /**
     * Establece el precio unitario del plato.
     * @param precioUnitario precio por unidad
     */
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
}
