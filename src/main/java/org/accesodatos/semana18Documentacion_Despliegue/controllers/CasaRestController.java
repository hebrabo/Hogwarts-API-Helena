package org.accesodatos.semana18Documentacion_Despliegue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.CasaDTO;
import org.accesodatos.semana18Documentacion_Despliegue.services.CasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/casas")
@RequiredArgsConstructor
public class CasaRestController {

    private final CasaService casaService;

    @GetMapping
    @Operation(summary = "Obtiene todas las casas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de casas recuperada con éxito"),
            @ApiResponse(responseCode = "204", description = "No existen casas en el sistema")
    })
    public ResponseEntity<List<CasaDTO>> obtenerTodas() {
        List<CasaDTO> casas = casaService.obtenerTodas();
        if (casas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(casas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una casa por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Casa encontrada"),
            @ApiResponse(responseCode = "404", description = "La casa solicitada no existe")
    })
    public ResponseEntity<CasaDTO> obtenerPorId(@PathVariable Long id) {
        CasaDTO casa = casaService.obtenerCasaPorId(id);
        return ResponseEntity.ok(casa);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Busca casas por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Casas encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay ninguna casa con ese nombre")
    })
    public ResponseEntity<List<CasaDTO>> obtenerPorNombre(@PathVariable String nombre) {
        List<CasaDTO> casas = casaService.obtenerCasaPorNombre(nombre);
        if (casas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(casas);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una casa por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Casa eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se puede borrar: Casa no encontrada")
    })
    public ResponseEntity<Void> borrarCasa(@PathVariable Long id) {
        casaService.borrarCasa(id);
        return ResponseEntity.noContent().build();
    }
}