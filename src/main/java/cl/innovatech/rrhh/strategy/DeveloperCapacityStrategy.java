package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.stereotype.Component;

@Component("dev")
public class DeveloperCapacityStrategy implements CapacityStrategy {

    @Override
    public int calcularDisponibilidad(Empleado e) {
        return 40 - e.getHorasAsignadas();
    }
}