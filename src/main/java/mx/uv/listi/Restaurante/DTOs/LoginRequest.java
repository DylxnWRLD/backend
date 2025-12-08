package mx.uv.listi.Restaurante.DTOs;

/**
 * DTO que representa la solicitud de inicio de sesión de un usuario.
 * Contiene las credenciales necesarias para autenticar a un usuario
 * dentro del sistema.
 */
public class LoginRequest {

    /**
     * Correo electrónico del usuario.
     */
    public String email;

    /**
     * Contraseña del usuario.
     */
    public String password;
}
