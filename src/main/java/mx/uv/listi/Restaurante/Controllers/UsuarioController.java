package mx.uv.listi.Restaurante.Controllers;

import mx.uv.listi.Restaurante.Services.UsuarioService;
import mx.uv.listi.Restaurante.DTOs.RegistroRequest;
import mx.uv.listi.Restaurante.DTOs.LoginRequest;
import mx.uv.listi.Restaurante.DTOs.UsuarioResponse;

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
        return "¡El controlador de Usuarios está vivo!";
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
}
