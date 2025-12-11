package mx.uv.listi.Restaurante.DTOs;

/**
 * DTO que representa la solicitud de registro de un nuevo usuario.
 * Contiene los datos necesarios para crear una cuenta dentro del sistema.
 */
public class RegistroRequest {

    /**
     * Nombre del usuario que se desea registrar.
     */
    public String nombre;

    /**
     * Correo electrónico del usuario.
     */
    public String email;

    /**
     * Contraseña elegida por el usuario.
     */
    public String password;
}
