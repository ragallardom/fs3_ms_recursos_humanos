package cl.innovatech.rrhh.controller;

import cl.innovatech.rrhh.model.Empleado;
import cl.innovatech.rrhh.service.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTest {

    @Mock
    private EmpleadoService empleadoService;

    @InjectMocks
    private EmpleadoController empleadoController;

    @Test
    void testObtenerDetalle_DebeRetornarEmpleado() {
        Empleado emp = Empleado.builder().id(1L).nombre("Ana").build();
        when(empleadoService.buscarPorId(1L)).thenReturn(emp);

        ResponseEntity<Empleado> response = empleadoController.obtenerDetalle(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Ana", response.getBody().getNombre());
    }

    @Test
    void testObtenerCapacidad_DebeRetornarCapacidad() {
        when(empleadoService.calcularDisponibilidad(1L)).thenReturn(40.0);

        ResponseEntity<Double> response = empleadoController.obtenerCapacidad(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(40.0, response.getBody());
    }

    @Test
    void testCrearEmpleado_DebeRetornar201() {
        Empleado emp = Empleado.builder().nombre("Ana").build();
        when(empleadoService.crearEmpleado(any(Empleado.class))).thenReturn(emp);

        ResponseEntity<Empleado> response = empleadoController.crearEmpleado(emp);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void testObtenerTodos_DebeRetornarLista() {
        Empleado emp = Empleado.builder().id(1L).nombre("Ana").build();
        when(empleadoService.obtenerTodos()).thenReturn(Collections.singletonList(emp));

        ResponseEntity<List<Empleado>> response = empleadoController.obtenerTodos();
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testActualizarEmpleado_DebeRetornarEmpleado() {
        Empleado emp = Empleado.builder().id(1L).nombre("Ana Updated").build();
        when(empleadoService.actualizarEmpleado(eq(1L), any(Empleado.class))).thenReturn(emp);

        ResponseEntity<Empleado> response = empleadoController.actualizarEmpleado(1L, emp);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testEliminarEmpleado_DebeRetornar204() {
        doNothing().when(empleadoService).eliminarEmpleado(1L);

        ResponseEntity<Void> response = empleadoController.eliminarEmpleado(1L);
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
    }
}
