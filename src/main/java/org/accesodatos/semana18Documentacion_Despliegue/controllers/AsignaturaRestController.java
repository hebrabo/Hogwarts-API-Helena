package org.accesodatos.semana18Documentacion_Despliegue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.AsignaturaDTO;
import org.accesodatos.semana18Documentacion_Despliegue.services.AsignaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
@RequiredArgsConstructor
public class AsignaturaRestController {

    private final AsignaturaService asignaturaService;

    @GetMapping
    @Operation(summary = "Obtiene todas las asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de asignaturas recuperada con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay asignaturas registradas")
    })
    public ResponseEntity<List<AsignaturaDTO>> obtenerTodas() {
        List<AsignaturaDTO> asignaturas = asignaturaService.obtenerTodas();
        if (asignaturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una asignatura por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignatura encontrada"),
            @ApiResponse(responseCode = "404", description = "La asignatura no existe")
    })
    public ResponseEntity<AsignaturaDTO> obtenerPorId(@PathVariable Long id) {
        AsignaturaDTO asignatura = asignaturaService.obtenerAsignaturaPorId(id);
        return ResponseEntity.ok(asignatura);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Busca asignaturas por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignaturas encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay coincidencias para ese nombre")
    })
    public ResponseEntity<List<AsignaturaDTO>> obtenerPorNombre(@PathVariable String nombre) {
        List<AsignaturaDTO> asignaturas = asignaturaService.obtenerAsignaturaPorNombre(nombre);
        if (asignaturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asignaturas);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una asignatura por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asignatura eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada"),
            @ApiResponse(responseCode = "409", description = "Conflicto: No se puede borrar una asignatura con alumnos matriculados")
    })
    public ResponseEntity<Void> borrarAsignatura(@PathVariable Long id) {
        asignaturaService.borrarAsignatura(id);
        return ResponseEntity.noContent().build();
    }
}