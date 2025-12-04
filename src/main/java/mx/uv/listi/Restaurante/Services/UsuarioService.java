package mx.uv.listi.Restaurante.Services;

import mx.uv.listi.Restaurante.Models.Usuario;
import mx.uv.listi.Restaurante.Repository.UsuarioRepository;
import mx.uv.listi.Restaurante.DTOs.RegistroRequest;
import mx.uv.listi.Restaurante.DTOs.LoginRequest;
import mx.uv.listi.Restaurante.DTOs.UsuarioResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Registro
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

    // Login
    public UsuarioResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
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
