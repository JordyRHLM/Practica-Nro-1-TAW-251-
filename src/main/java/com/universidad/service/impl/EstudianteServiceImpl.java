package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @PostConstruct
    public void init() {
        estudianteRepository.init(); // Inicializa los datos de ejemplo
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            estudiantesDTO.add(convertToDTO(estudiante));
        }
        return estudiantesDTO;
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id);
        return estudiante != null ? convertToDTO(estudiante) : null;
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id);
        if (estudianteExistente != null) {
            estudianteExistente.setNombre(estudianteDTO.getNombre());
            estudianteExistente.setApellido(estudianteDTO.getApellido());
            estudianteExistente.setEmail(estudianteDTO.getEmail());
            estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
            estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());
            estudianteRepository.save(estudianteExistente); // Guarda el estudiante actualizado
            return convertToDTO(estudianteExistente); // Retorna el estudiante actualizado en formato DTO
        }
        return null;
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO); // Convertimos DTO a entidad
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante); // Guardamos en el repositorio
        return convertToDTO(estudianteGuardado); // Retornamos el DTO del estudiante creado
    }

    @Override
    public boolean eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id);
        if (estudiante != null) {
            estudianteRepository.deleteById(id); // Elimina el estudiante del repositorio
            return true;
        }
        return false; // Retorna false si el estudiante no fue encontrado
    }

    // Método para convertir Estudiante a EstudianteDTO
    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .build();
    }

    // Método para convertir EstudianteDTO a Estudiante
    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
                .build();
    }
}
