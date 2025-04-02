package com.universidad.controller;

import com.universidad.dto.EstudianteDTO;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Obtener todos los estudiantes
    @GetMapping("/estudiantes")
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    // Obtener un estudiante por su ID
    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorId(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorId(id);
        return estudiante != null ? ResponseEntity.ok(estudiante) : ResponseEntity.notFound().build();
    }

    // Actualizar un estudiante
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return estudianteActualizado != null ? ResponseEntity.ok(estudianteActualizado) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo estudiante
    @PostMapping("/estudiantes")
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteCreado = estudianteService.crearEstudiante(estudianteDTO);
        return ResponseEntity.status(201).body(estudianteCreado); // Devuelve el estado 201 y el estudiante creado
    }

    // Eliminar un estudiante por su ID
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        boolean eliminado = estudianteService.eliminarEstudiante(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); // Retorna 204 si fue eliminado, 404 si no se encontró
    }
}
