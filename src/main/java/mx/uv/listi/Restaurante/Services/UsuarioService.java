package mx.uv.listi.Restaurante.Services;

import java.util.List;
import java.util.stream.Collectors;

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
        Usuario usuario = usuarioRepository.findByEmail(request.email).
            orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!BCrypt.checkpw(request.password, usuario.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }

    /**
     * Lista todos los usuarios registrados en el sistema. 
     * Solo para uso de administrador.
     * * @return Una lista de UsuarioResponse con los datos de todos los usuarios.
     */
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getNombre(), u.getEmail()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza la información de un usuario existente. 
     * La contraseña no se actualiza a través de este método.
     * * @param id El ID del usuario a actualizar.
     * @param request Datos a actualizar (nombre, email).
     * @return UsuarioResponse con los datos actualizados.
     * @throws RuntimeException si el usuario no es encontrado.
     */
    public UsuarioResponse actualizar(Long id, RegistroRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para actualizar"));

        // Nota: Por seguridad, evitamos actualizar el password en este endpoint
        usuario.setNombre(request.nombre);
        usuario.setEmail(request.email); // Se puede añadir validación de email único aquí

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new UsuarioResponse(
                usuarioActualizado.getId(),
                usuarioActualizado.getNombre(),
                usuarioActualizado.getEmail()
        );
    }
    
    /**
     * Elimina un usuario por su ID.
     * * @param id El ID del usuario a eliminar.
     */
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado para eliminar");
        }
        usuarioRepository.deleteById(id);
    }
}
