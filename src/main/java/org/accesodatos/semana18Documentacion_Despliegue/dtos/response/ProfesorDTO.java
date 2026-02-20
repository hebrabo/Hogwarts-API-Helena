package org.accesodatos.semana18Documentacion_Despliegue.dtos.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfesorDTO {
    private Long id;
    private String nombre; // El mapper es el que concatenará "Nombre" + "Apellido"
    private String asignatura;
    private LocalDate fechaInicio;
}
