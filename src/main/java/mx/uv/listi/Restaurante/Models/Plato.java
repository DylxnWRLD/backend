package mx.uv.listi.Restaurante.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "platos")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, name = "precio_base")
    private Double precioBase;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean disponible = true;

    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PedidoDetalle> detalles = new HashSet<>();

    public Plato() {
    }

    public Plato(String nombre, String descripcion, Double precioBase) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Set<PedidoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<PedidoDetalle> detalles) {
        this.detalles = detalles;
    }

    public void addDetalle(PedidoDetalle detalle) {
        this.detalles.add(detalle);
        detalle.setPlato(this);
    }

    public void removeDetalle(PedidoDetalle detalle) {
        this.detalles.remove(detalle);
        detalle.setPlato(null);
    }
}