package mx.uv.listi.Restaurante.Repository;

import mx.uv.listi.Restaurante.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar por email, útil para el login
    Usuario findByEmail(String email);
}