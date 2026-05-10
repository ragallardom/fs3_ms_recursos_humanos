package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;

public interface CapacityStrategy {

    double calcularDisponibilidad(Empleado empleado);

    String getCargoAsociado();
}