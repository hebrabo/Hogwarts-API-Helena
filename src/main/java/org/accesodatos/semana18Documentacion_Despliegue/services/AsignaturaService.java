package org.accesodatos.semana18Documentacion_Despliegue.services;

import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.AsignaturaDTO;

import java.util.List;

public interface AsignaturaService {
    List<AsignaturaDTO> obtenerTodas();

    AsignaturaDTO obtenerAsignaturaPorId(Long id);

    List<AsignaturaDTO> obtenerAsignaturaPorNombre(String nombre);

    // DELETE (RESTRICT)
    void borrarAsignatura(Long id);
}
