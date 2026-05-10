package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.stereotype.Component;

@Component("ux")
public class UXCapacityStrategy implements CapacityStrategy {

    @Override
    public int calcularDisponibilidad(Empleado e) {
        return 35 - e.getHorasAsignadas();
    }
}