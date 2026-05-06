package cl.innovatech.rrhh.service;

import cl.innovatech.rrhh.model.Empleado;
import cl.innovatech.rrhh.repository.EmpleadoRepository;
import cl.innovatech.rrhh.strategy.CapacityStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final Map<String, CapacityStrategy> strategies;

    public EmpleadoService(EmpleadoRepository empleadoRepository,
                           Map<String, CapacityStrategy> strategies) {
        this.empleadoRepository = empleadoRepository;
        this.strategies = strategies;
    }

    public int calcularDisponibilidad(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        String cargo = empleado.getCargo().toLowerCase();

        CapacityStrategy strategy = strategies.get(cargo);

        if (strategy == null) {
            throw new RuntimeException("No existe estrategia para el cargo: " + cargo);
        }

        return strategy.calcularDisponibilidad(empleado);
    }
}