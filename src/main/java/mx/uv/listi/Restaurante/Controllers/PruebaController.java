package mx.uv.listi.Restaurante.Controllers; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/prueba")
public class PruebaController {

    /**
     * Endpoint existente: GET http://localhost:8080/api/prueba/estado
     */
    @GetMapping("/estado") 
    public String obtenerEstado() {
        return "El Backend de Spring Boot está activo y funcionando.";
    }

    // AÑADE ESTE NUEVO ENDPOINT
    /**
     * Nuevo Endpoint: GET http://localhost:8080/api/prueba/version
     * Devuelve información de la aplicación.
     */
    @GetMapping("/version") 
    public String obtenerVersion() {
        // En un proyecto real, esto devolvería la versión del archivo pom.xml
        return "Listi Restaurante API v1.0 - Usando Spring Boot y MariaDB";
    }
}