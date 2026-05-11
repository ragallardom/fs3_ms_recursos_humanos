package cl.innovatech.rrhh.controller;

import cl.innovatech.rrhh.model.Empleado;
import cl.innovatech.rrhh.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rrhh/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.buscarPorId(id));
    }

    @GetMapping("/{id}/capacity")
    public ResponseEntity<Double> obtenerCapacidad(@PathVariable Long id) {
        double capacidad = empleadoService.calcularDisponibilidad(id);
        return ResponseEntity.ok(capacidad);
    }
}