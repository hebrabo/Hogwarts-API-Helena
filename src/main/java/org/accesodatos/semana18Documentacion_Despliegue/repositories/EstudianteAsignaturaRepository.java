package org.accesodatos.semana18Documentacion_Despliegue.repositories;

import org.accesodatos.semana18Documentacion_Despliegue.models.EstudianteAsignatura;
import org.accesodatos.semana18Documentacion_Despliegue.models.EstudianteAsignaturaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignatura, EstudianteAsignaturaKey> {
}