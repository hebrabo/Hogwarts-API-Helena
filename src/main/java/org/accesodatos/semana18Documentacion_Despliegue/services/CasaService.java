package org.accesodatos.semana18Documentacion_Despliegue.services;

import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.CasaDTO;

import java.util.List;

public interface CasaService {
    List<CasaDTO> obtenerTodas();

    CasaDTO obtenerCasaPorId(Long id);

    List<CasaDTO> obtenerCasaPorNombre(String nombre);

    // --- DELETE (SET NULL)  ---
    void borrarCasa(Long id);
}
