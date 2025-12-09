package mx.uv.listi.Restaurante.Services;

import mx.uv.listi.Restaurante.Models.Plato;
import mx.uv.listi.Restaurante.Repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    /**
     * Obtiene todos los platos en la base de datos.
     */
    public List<Plato> obtenerTodosLosPlatos() {
        return platoRepository.findAll();
    }
    
    /**
     * Obtiene solo los platos que tienen el atributo 'disponible' en true.
     */
    public List<Plato> obtenerPlatosDisponibles() {
        // Usa el método personalizado definido en PlatoRepository
        return platoRepository.findByDisponibleTrue();
    }

    /**
     * Obtiene un plato por su ID.
     */
    public Optional<Plato> obtenerPlatoPorId(Long id) {
        return platoRepository.findById(id);
    }
    
    /**
     * Crea o actualiza un plato.
     */
    @Transactional
    public Plato guardarPlato(Plato plato) {
        return platoRepository.save(plato);
    }
    
    /**
     * Cambia el estado de disponibilidad de un plato.
     */
    @Transactional
    public Plato cambiarDisponibilidad(Long id, boolean disponible) {
        
        Plato plato = platoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Plato con ID " + id + " no encontrado para actualizar su disponibilidad."));
        
        plato.setDisponible(disponible);
        
        return platoRepository.save(plato);
    }

    /**
     * Elimina un plato por su ID.
     */
    public void eliminarPlato(Long id) {
        platoRepository.deleteById(id);
    }
}