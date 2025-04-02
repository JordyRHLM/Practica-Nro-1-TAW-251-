package com.universidad.service;

import com.universidad.dto.EstudianteDTO;
import java.util.List;

public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes();
    EstudianteDTO obtenerEstudiantePorId(Long id); // Método para obtener un estudiante por ID
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO); // Método para actualizar un estudiante
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO); // Método para crear un estudiante
    boolean eliminarEstudiante(Long id); // Método para eliminar un estudiante por su ID
}
