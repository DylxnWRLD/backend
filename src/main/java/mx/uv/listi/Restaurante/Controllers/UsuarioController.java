package mx.uv.listi.Restaurante.Controllers;

import mx.uv.listi.Restaurante.Services.UsuarioService;
import mx.uv.listi.Restaurante.DTOs.RegistroRequest;
import mx.uv.listi.Restaurante.DTOs.LoginRequest;
import mx.uv.listi.Restaurante.DTOs.UsuarioResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/test")
    public String probarAcceso() {
        return "¡El controlador de Usuarios está vivo!";
    }

    @PostMapping("/registro")
    public UsuarioResponse registrar(@RequestBody RegistroRequest request) {
        return usuarioService.registrar(request);
    }

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody LoginRequest request) {
        return usuarioService.login(request);
    }
}
