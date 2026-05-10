package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("ux")
public class UXCapacityStrategy implements CapacityStrategy {

    @Value("${company.capacity.ux.max-hours:35.0}")
    private double maxWeeklyHours;

    private static final String CARGO_OBJETIVO = "UX_DESIGNER";

    @Override
    public double calcularDisponibilidad(Empleado e) {
        if (e == null) return 0.0;
        return Math.max(0, maxWeeklyHours - e.getHorasAsignadas());
    }

    @Override
    public String getCargoAsociado() {
        return CARGO_OBJETIVO;
    }
}