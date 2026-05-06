package cl.innovatech.rrhh.controller;

import cl.innovatech.rrhh.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rrhh/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/{id}/capacity")
    public int obtenerCapacidad(@PathVariable Long id) {
        return empleadoService.calcularDisponibilidad(id);
    }
}