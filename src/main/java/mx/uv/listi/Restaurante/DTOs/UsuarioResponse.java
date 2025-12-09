package mx.uv.listi.Restaurante.DTOs;

/**
 * DTO que representa la respuesta enviada al cliente con la información
 * del usuario ya sea después de registrarse o iniciar sesión.
 */
public class UsuarioResponse {

    /**
     * Identificador único del usuario.
     */
    public Long id;

    /**
     * Nombre del usuario.
     */
    public String nombre;

    /**
     * Correo electrónico del usuario.
     */
    public String email;

    /**
     * Crea una nueva instancia de {@code UsuarioResponse}.
     *
     * @param id     identificador único del usuario
     * @param nombre nombre del usuario
     * @param email  correo electrónico del usuario
     */
    public UsuarioResponse(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
}
