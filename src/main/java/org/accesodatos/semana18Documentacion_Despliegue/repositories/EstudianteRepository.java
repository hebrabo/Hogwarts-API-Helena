package org.accesodatos.semana18Documentacion_Despliegue.repositories;

import org.accesodatos.semana18Documentacion_Despliegue.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
}
