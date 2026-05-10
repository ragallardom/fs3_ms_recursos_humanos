package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class UXDesignerCapacityStrategy implements CapacityStrategy {
    @Override
    public double calcularDisponibilidad(Empleado e) {
        double disponible = (double) (e.getCapacidadMaxima() - e.getHorasAsignadas());
        return (disponible / e.getCapacidadMaxima()) * 0.9;
    }

    @Override
    public String getCargoAsociado() {
        return "UX_DESIGNER";
    }
}