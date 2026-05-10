package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.stereotype.Component;

@Component("ux-designer-specialized")
public class UXDesignerCapacityStrategy implements CapacityStrategy {

    private static final String CARGO = "UX_DESIGNER_SPECIALIZED";

    @Override
    public double calcularDisponibilidad(Empleado e) {
        if (e == null || e.getCapacidadMaxima() <= 0) return 0.0;

        double disponibleBruto = e.getCapacidadMaxima() - e.getHorasAsignadas();
        return Math.max(0, disponibleBruto * 0.9);
    }

    @Override
    public String getCargoAsociado() {
        return CARGO;
    }
}