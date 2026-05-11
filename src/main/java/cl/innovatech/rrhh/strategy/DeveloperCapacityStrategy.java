package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.stereotype.Component;

@Component("dev")
public class DeveloperCapacityStrategy implements CapacityStrategy {
    private static final double MAX_HOURS = 40.0;

    @Override
    public double calcularDisponibilidad(Empleado e) {
        if (e == null) return 0.0;
        return Math.max(0, e.getCapacidadMaxima() - e.getHorasAsignadas());
    }

    @Override
    public String getCargoAsociado() {
        return "DEVELOPER";
    }
}