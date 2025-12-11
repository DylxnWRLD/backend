package mx.uv.listi.Restaurante.Repository;

import mx.uv.listi.Restaurante.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD y consultas
 * relacionadas con la entidad {@link Usuario}.
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Buscar un usuario por su correo electrónico.
     *
     * @param email correo electrónico del usuario.
     * @return un {@link Optional} que contiene el usuario si existe.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verificar si existe un usuario registrado con el correo electrónico dado.
     *
     * @param email correo a verificar.
     * @return {@code true} si el usuario existe, {@code false} en caso de que no.
     */
    boolean existsByEmail(String email);
}
