package mx.uv.listi.Restaurante.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import mx.uv.listi.Restaurante.DTOs.LoginRequest;
import mx.uv.listi.Restaurante.DTOs.RegistroRequest;
import mx.uv.listi.Restaurante.DTOs.UsuarioResponse;
import mx.uv.listi.Restaurante.Models.Usuario;
import mx.uv.listi.Restaurante.Repository.UsuarioRepository;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada
 * con los usuarios, incluyendo registro, autenticación y validación.
 *
 * <p>
 * Este servicio utiliza {@link UsuarioRepository} para interactuar con la base
 * de datos y {@link BCrypt} para manejar el hashing seguro de contraseñas.
 * </p>
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request objeto {@link RegistroRequest} con nombre, email y contraseña.
     * @return un {@link UsuarioResponse} con la información básica del usuario creado.
     * @throws RuntimeException si el correo ya existe en el sistema.
     */
    public UsuarioResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Encriptar contraseña
        String hash = BCrypt.hashpw(request.password, BCrypt.gensalt());

        Usuario usuario = new Usuario(
                request.nombre,
                request.email,
                hash
        );

        usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }

    /**
     * Autentica a un usuario usando su correo y contraseña.
     *
     *
     * @param request objeto {@link LoginRequest} con email y contraseña.
     * @return un {@link UsuarioResponse} con la información del usuario autenticado.
     * @throws RuntimeException si el usuario no existe o la contraseña es incorrecta.
     */
    public UsuarioResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar contraseña
        if (!BCrypt.checkpw(request.password, usuario.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
