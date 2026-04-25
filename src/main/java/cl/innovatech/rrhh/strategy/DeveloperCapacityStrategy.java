package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class DeveloperCapacityStrategy implements CapacityStrategy {
    @Override
    public double calcularDisponibilidad(Empleado e) {
        return (double) (e.getCapacidadMaxima() - e.getHorasAsignadas()) / e.getCapacidadMaxima();
    }

    @Override
    public String getCargoAsociado() {
        return "DEVELOPER";
    }
}