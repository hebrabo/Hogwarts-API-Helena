package org.accesodatos.semana18Documentacion_Despliegue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.request.create.EstudianteCreateDTO;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.request.update.EstudianteUpdateDTO;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.EstudianteDTO;
import org.accesodatos.semana18Documentacion_Despliegue.services.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    @GetMapping
    @Operation(summary = "Obtiene todos los estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes recuperada con éxito"),
            @ApiResponse(responseCode = "204", description = "No se han encontrado estudiantes")
    })
    public ResponseEntity<List<EstudianteDTO>> obtenerTodos() {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodos();
        if (estudiantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado y devuelto"),
            @ApiResponse(responseCode = "404", description = "El ID proporcionado no corresponde a ningún estudiante")
    })
    public ResponseEntity<EstudianteDTO> obtenerPorId(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorId(id);
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Busca estudiantes por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coincidencias encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay estudiantes con ese nombre")
    })
    public ResponseEntity<List<EstudianteDTO>> obtenerPorNombre(@PathVariable String nombre) {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerEstudiantePorNombre(nombre);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Los datos del estudiante no son válidos")
    })
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO estudianteCreado = estudianteService.crearEstudiante(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCreado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica los datos de un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se puede actualizar: Estudiante no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos")
    })
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @Valid @RequestBody EstudianteUpdateDTO dto) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, dto);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se puede borrar: Estudiante no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto: El estudiante tiene una mascota asociada")
    })
    public ResponseEntity<Void> borrarEstudiante(@PathVariable Long id) {
        estudianteService.borrarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}