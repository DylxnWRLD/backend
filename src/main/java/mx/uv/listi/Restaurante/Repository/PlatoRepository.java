package mx.uv.listi.Restaurante.Repository;

import mx.uv.listi.Restaurante.Models.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // <-- CORRECCIÓN: Usar la List de Java

public interface PlatoRepository extends JpaRepository<Plato, Long> {
    
    // Método para buscar platos disponibles
    List<Plato> findByDisponibleTrue();

    
}