package org.accesodatos.semana18Documentacion_Despliegue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.ProfesorDTO;
import org.accesodatos.semana18Documentacion_Despliegue.services.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorRestController {

    private final ProfesorService profesorService;

    @GetMapping
    @Operation(summary = "Obtiene todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de profesores recuperada con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay profesores registrados en el claustro")
    })
    public ResponseEntity<List<ProfesorDTO>> obtenerTodos() {
        List<ProfesorDTO> profesores = profesorService.obtenerTodos();
        if (profesores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un profesor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado ningún profesor con ese ID")
    })
    public ResponseEntity<ProfesorDTO> obtenerPorId(@PathVariable Long id) {
        ProfesorDTO profesor = profesorService.obtenerProfesorPorId(id);
        return ResponseEntity.ok(profesor);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Busca profesores por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesores encontrados"),
            @ApiResponse(responseCode = "204", description = "No hay profesores que coincidan con el nombre proporcionado")
    })
    public ResponseEntity<List<ProfesorDTO>> obtenerPorNombre(@PathVariable String nombre) {
        List<ProfesorDTO> profesores = profesorService.obtenerProfesorPorNombre(nombre);
        if (profesores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profesores);
    }
}