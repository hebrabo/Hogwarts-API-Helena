package org.accesodatos.semana18Documentacion_Despliegue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.MascotaDTO;
import org.accesodatos.semana18Documentacion_Despliegue.services.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaRestController {

    private final MascotaService mascotaService;

    @GetMapping
    @Operation(summary = "Obtiene todas las mascotas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay mascotas registradas en el sistema")
    })
    public ResponseEntity<List<MascotaDTO>> obtenerTodas() {
        List<MascotaDTO> mascotas = mascotaService.obtenerTodas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una mascota por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mascota encontrada"),
            @ApiResponse(responseCode = "404", description = "No existe ninguna mascota con ese identificador")
    })
    public ResponseEntity<MascotaDTO> obtenerPorId(@PathVariable Long id) {
        MascotaDTO mascota = mascotaService.obtenerMascotaPorId(id);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Busca mascotas por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han encontrado mascotas con ese nombre"),
            @ApiResponse(responseCode = "204", description = "No hay ninguna mascota que coincida con la búsqueda")
    })
    public ResponseEntity<List<MascotaDTO>> obtenerPorNombre(@PathVariable String nombre) {
        List<MascotaDTO> mascotas = mascotaService.obtenerMascotasPorNombre(nombre);
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }
}