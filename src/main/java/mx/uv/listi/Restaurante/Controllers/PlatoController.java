package mx.uv.listi.Restaurante.Controllers;

import mx.uv.listi.Restaurante.Models.Plato;
import mx.uv.listi.Restaurante.Services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importar para el código de estado 201
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // Importar para el método POST
import org.springframework.web.bind.annotation.RequestBody; // Importar para recibir el JSON
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@CrossOrigin(origins = "*")
/**
 * Controlador REST para gestionar las operaciones relacionadas con los Platos (productos).
 * Mapeo base: http://localhost:8080/api/platos
 */
@RestController
@RequestMapping("/api/platos") 
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    // --- 1. Métodos de Consulta (GET) ---

    /**
     * Endpoint para obtener la lista de *todos* los platos.
     * Mapeado a: GET /api/platos
     */
    @GetMapping
    public List<Plato> obtenerTodosLosPlatos() {
        return platoService.obtenerTodosLosPlatos();
    }
    
    /**
     * Endpoint para obtener solo los platos que están disponibles para la venta (el catálogo).
     * Mapeado a: GET /api/platos/disponibles
     */
    @GetMapping("/disponibles")
    public List<Plato> obtenerPlatosDisponibles() {
        return platoService.obtenerPlatosDisponibles();
    }

    
    /**
     * Endpoint para crear un nuevo Plato en la base de datos.
     * Mapeado a: POST /api/platos
     * @param plato El objeto Plato recibido en el cuerpo de la petición.
     * @return El plato guardado con su ID y un código 201 Created.
     */
    @PostMapping
    public ResponseEntity<Plato> crearPlato(@RequestBody Plato plato) {
        Plato nuevoPlato = platoService.guardarPlato(plato);
        return new ResponseEntity<>(nuevoPlato, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plato> actualizarPlato(@PathVariable Long id, @RequestBody Plato plato) {
        // Buscamos si existe
        return platoService.obtenerPlatoPorId(id)
                .map(platoExistente -> {
                    plato.setId(id); // Forzamos que el ID del objeto coincida con la URL
                    Plato actualizado = platoService.guardarPlato(plato);
                    return new ResponseEntity<>(actualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlato(@PathVariable Long id) {
        if (platoService.obtenerPlatoPorId(id).isPresent()) {
            platoService.eliminarPlato(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204: Éxito sin contenido
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}