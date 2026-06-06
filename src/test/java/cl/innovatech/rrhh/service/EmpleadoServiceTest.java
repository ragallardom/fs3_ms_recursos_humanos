package cl.innovatech.rrhh.service;


import cl.innovatech.rrhh.exception.EmpleadoNotFoundException;
import cl.innovatech.rrhh.model.Empleado;
import cl.innovatech.rrhh.repository.EmpleadoRepository;
import cl.innovatech.rrhh.strategy.CapacityStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private List<CapacityStrategy> capacityStrategies; // Mockeamos la lista de estrategias

    @InjectMocks
    private EmpleadoService empleadoService;

    private Empleado empleadoPrueba;

    @BeforeEach
    void setUp() {
        empleadoPrueba = Empleado.builder()
                .id(1L)
                .nombre("Ana Soto")
                .email("ana.soto@innovatech.cl")
                .cargo("DEVELOPER")
                .horasAsignadas(30)
                .capacidadMaxima(40)
                .build();
    }

    @Test
    void testBuscarPorId_DebeRetornarEmpleadoSiExiste() {
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleadoPrueba));

        Empleado resultado = empleadoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Ana Soto", resultado.getNombre());
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void testCrearEmpleado_DebeGuardarYRetornarEmpleado() {
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoPrueba);

        Empleado resultado = empleadoService.crearEmpleado(empleadoPrueba);

        assertNotNull(resultado);
        assertEquals("DEVELOPER", resultado.getCargo());
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testActualizarEmpleado_DebeModificarYGuardar() {
        Empleado datosNuevos = Empleado.builder()
                .nombre("Ana Modificada")
                .email("ana.nueva@innovatech.cl")
                .cargo("SENIOR_DEVELOPER")
                .horasAsignadas(40)
                .capacidadMaxima(45)
                .build();

        // buscarPorId es usado internamente por actualizarEmpleado
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleadoPrueba));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoPrueba);

        Empleado resultado = empleadoService.actualizarEmpleado(1L, datosNuevos);

        assertNotNull(resultado);
        assertEquals("Ana Modificada", resultado.getNombre());
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testEliminarEmpleado_DebeBorrarSiExiste() {
        when(empleadoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(empleadoRepository).deleteById(1L);

        empleadoService.eliminarEmpleado(1L);

        verify(empleadoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarEmpleado_DebeLanzarExcepcionSiNoExiste() {
        when(empleadoRepository.existsById(99L)).thenReturn(false);

        assertThrows(EmpleadoNotFoundException.class, () -> {
            empleadoService.eliminarEmpleado(99L);
        });

        verify(empleadoRepository, never()).deleteById(anyLong());
    }
}
