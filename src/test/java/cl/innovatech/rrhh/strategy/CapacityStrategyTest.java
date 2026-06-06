package cl.innovatech.rrhh.strategy;

import cl.innovatech.rrhh.model.Empleado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CapacityStrategyTest {

    @Test
    void testDeveloperCapacityStrategy() {
        DeveloperCapacityStrategy strategy = new DeveloperCapacityStrategy();
        assertEquals("DEVELOPER", strategy.getCargoAsociado());

        Empleado emp = Empleado.builder().capacidadMaxima(40).horasAsignadas(30).build();
        assertEquals(10.0, strategy.calcularDisponibilidad(emp));
        assertEquals(0.0, strategy.calcularDisponibilidad(null));
    }

    @Test
    void testUXDesignerCapacityStrategy() {
        UXDesignerCapacityStrategy strategy = new UXDesignerCapacityStrategy();
        assertEquals("UX", strategy.getCargoAsociado());

        Empleado emp = Empleado.builder().capacidadMaxima(40).horasAsignadas(30).build();
        assertEquals(9.0, strategy.calcularDisponibilidad(emp)); // (40-30)*0.9
        assertEquals(0.0, strategy.calcularDisponibilidad(null));
    }
}
