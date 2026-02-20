package org.accesodatos.semana18Documentacion_Despliegue.services;

import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.MascotaDTO;

import java.util.List;

public interface MascotaService {
    List<MascotaDTO> obtenerTodas();

    MascotaDTO obtenerMascotaPorId(Long id);

    List<MascotaDTO> obtenerMascotasPorNombre(String nombre);
}
