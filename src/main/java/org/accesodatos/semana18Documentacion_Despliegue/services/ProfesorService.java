package org.accesodatos.semana18Documentacion_Despliegue.services;

import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.ProfesorDTO;

import java.util.List;

public interface ProfesorService {
    List<ProfesorDTO> obtenerTodos();

    ProfesorDTO obtenerProfesorPorId(Long id);

    List<ProfesorDTO> obtenerProfesorPorNombre(String nombre);
}
