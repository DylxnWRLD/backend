package mx.uv.listi.Restaurante.Controllers;

import mx.uv.listi.Restaurante.Services.UsuarioService;
import mx.uv.listi.Restaurante.DTOs.RegistroRequest;
import mx.uv.listi.Restaurante.DTOs.LoginRequest;
import mx.uv.listi.Restaurante.DTOs.UsuarioResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas
 * con los usuarios, tales como registro, inicio de sesión y pruebas de acceso.
 *
 * Proporciona endpoints bajo la ruta base "/api/usuarios".
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint de prueba para verificar que el controlador está funcionando.
     *
     * @return mensaje indicando que el controlador está activo.
     */
    @GetMapping("/test")
    public String probarAcceso() {
        return "Funciona";
    }

    /**
     * Registra un nuevo usuario a partir de los datos enviados en el cuerpo de la solicitud.
     *
     * @param request objeto que contiene la información necesaria para el registro:
     *                nombre, correo, contraseña.
     * @return un {@link UsuarioResponse} con la información del usuario registrado.
     */
    @PostMapping("/registro")
    public UsuarioResponse registrar(@RequestBody RegistroRequest request) {
        return usuarioService.registrar(request);
    }

    /**
     * Autentica a un usuario con sus credenciales.
     *
     * @param request objeto que contiene el correo y la contraseña del usuario.
     * @return un {@link UsuarioResponse} con los datos del usuario autenticado.
     */
    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody LoginRequest request) {
        return usuarioService.login(request);
    }

    /**
     * Obtiene la lista completa de usuarios. Requiere permisos de administrador.
     * Mapeado a GET /api/usuarios
     * @return Lista de todos los usuarios (DTOs).
     */
    @GetMapping
    public List<UsuarioResponse> listarTodos() {
        return usuarioService.listarTodos();
    }
    
    /**
     * Actualiza la información de un usuario existente por su ID.
     * Mapeado a PUT /api/usuarios/{id}
     * @param id ID del usuario a actualizar.
     * @param request Datos a modificar (nombre, email).
     * @return El usuario con los datos actualizados.
     */
    @PutMapping("/{id}")
    public UsuarioResponse actualizar(@PathVariable Long id, @RequestBody RegistroRequest request) {
        return usuarioService.actualizar(id, request);
    }
    
    /**
     * Elimina un usuario por su ID.
     * Mapeado a DELETE /api/usuarios/{id}
     * @param id ID del usuario a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
