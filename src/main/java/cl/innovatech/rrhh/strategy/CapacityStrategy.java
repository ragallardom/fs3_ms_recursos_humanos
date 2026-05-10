package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;

public interface CapacityStrategy {
    int calcularDisponibilidad(Empleado empleado);
}
