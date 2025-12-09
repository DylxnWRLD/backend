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
import java.util.List;

/**
 * Entidad que representa a un usuario o cliente del restaurante dentro del sistema.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    /**
     * Identificador único del usuario. Se genera automáticamente mediante estrategia
     * de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario. No puede ser nulo y tiene una longitud máxima de 100 caracteres.
     */
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Correo electrónico del usuario. No puede ser nulo, debe ser único y tiene una
     * longitud máxima de 100 caracteres.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    /**
     * Hash de la contraseña del usuario. Se almacena de forma segura y no en texto plano.
     */
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /**
     * Lista de pedidos asociados al usuario.
     *
     * <p>
     * Esta es una relación bidireccional donde un usuario puede tener múltiples pedidos.
     * </p>
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pedido> pedidos;

    /**
     * Constructor vacío.
     */
    public Usuario() {
    }

    /**
     * Constructor para crear un usuario con los datos básicos.
     *
     * @param nombre nombre del usuario.
     * @param email correo electrónico del usuario.
     * @param passwordHash hash seguro de la contraseña.
     */
    public Usuario(String nombre, String email, String passwordHash) {
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el identificador del usuario.
     *
     * @return id del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id nuevo identificador del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return correo del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email nuevo correo del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el hash de la contraseña del usuario.
     *
     * @return hash de la contraseña.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Establece el hash de la contraseña del usuario.
     *
     * @param passwordHash nuevo hash de la contraseña.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Obtiene la lista de pedidos asociados al usuario.
     *
     * @return lista de pedidos.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Establece la lista de pedidos asociados al usuario.
     *
     * @param pedidos nueva lista de pedidos del usuario.
     */
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
