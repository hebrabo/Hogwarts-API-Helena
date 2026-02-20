package org.accesodatos.semana18Documentacion_Despliegue.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.request.create.EstudianteCreateDTO;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.request.update.EstudianteUpdateDTO;
import org.accesodatos.semana18Documentacion_Despliegue.dtos.response.EstudianteDTO;
import org.accesodatos.semana18Documentacion_Despliegue.mappers.EstudianteMapper;
import org.accesodatos.semana18Documentacion_Despliegue.models.Casa;
import org.accesodatos.semana18Documentacion_Despliegue.models.Estudiante;
import org.accesodatos.semana18Documentacion_Despliegue.repositories.CasaRepository;
import org.accesodatos.semana18Documentacion_Despliegue.repositories.EstudianteRepository;
import org.accesodatos.semana18Documentacion_Despliegue.services.EstudianteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CasaRepository casaRepository;
    private final EstudianteMapper estudianteMapper;

    @Override
    public List<EstudianteDTO> obtenerTodos() {
        return estudianteRepository.findAll()
                .stream()
                .map(estudianteMapper::toDto)
                .toList();
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));
        return estudianteMapper.toDto(estudiante);
    }

    @Override
    public List<EstudianteDTO> obtenerEstudiantePorNombre(String nombre) {
        return estudianteRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(estudianteMapper::toDto)
                .toList();
    }

    // --- MÉTODOS POST Y PUT ---

    @Override
    @Transactional
    public EstudianteDTO crearEstudiante(EstudianteCreateDTO dto) {
        Estudiante estudiante = estudianteMapper.toEntity(dto);

        if (dto.getCasaId() != null) {
            Casa casa = casaRepository.findById(dto.getCasaId())
                    .orElseThrow(() -> new EntityNotFoundException("Casa no encontrada con id: " + dto.getCasaId()));
            estudiante.setCasa(casa);
        }

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);
        return estudianteMapper.toDto(estudianteGuardado);
    }

    @Override
    @Transactional
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteUpdateDTO dto) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        estudianteMapper.updateEstudianteFromDto(dto, estudianteExistente);

        return estudianteMapper.toDto(estudianteRepository.save(estudianteExistente));
    }

    // --- MÉTODO DELETE ---

    // DELETE (CASCADE)
    @Override
    @Transactional
    public void borrarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        if (estudiante.getMascota() != null) {
            throw new IllegalStateException("No se puede expulsar a un estudiante con mascota activa.");
        }

        estudianteRepository.delete(estudiante);
    }
}