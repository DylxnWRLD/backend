package mx.uv.listi.Restaurante.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    public enum Estado {
        PENDIENTE,
        EN_PREPARACION,
        LISTO,
        ENTREGADO,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID de tipo Long para consistencia con MariaDB BIGINT

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @Column(name = "total")
    private Double total = 0.0;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoDetalle> detalles;

    public Pedido() {}

    @PreUpdate
    public void setFechaActualizacion() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // --- Getters y Setters Completos ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() { 
        return cliente; 
    }

    public void setCliente(Usuario cliente) { 
        this.cliente = cliente; 
    }

    public Estado getEstado() { 
        return estado; 
    }

    public void setEstado(Estado estado) { 
        this.estado = estado; 
    }

    public LocalDateTime getFechaCreacion() { 
        return fechaCreacion; 
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) { 
        this.fechaCreacion = fechaCreacion; 
    }

    public LocalDateTime getFechaActualizacion() { 
        return fechaActualizacion; 
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { 
        this.fechaActualizacion = fechaActualizacion; 
    }

    public Double getTotal() { 
        return total; 
    }

    public void setTotal(Double total) { 
        this.total = total; 
    }

    public List<PedidoDetalle> getDetalles() { 
        return detalles; 
    }

    public void setDetalles(List<PedidoDetalle> detalles) { 
        this.detalles = detalles; 
    }
}