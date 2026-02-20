package org.accesodatos.semana18Documentacion_Despliegue.repositories;

import org.accesodatos.semana18Documentacion_Despliegue.models.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    List<Asignatura> findByNombreContainingIgnoreCase(String nombre);
}
