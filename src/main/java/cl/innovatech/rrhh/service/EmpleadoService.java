package cl.innovatech.rrhh.service;

import cl.innovatech.rrhh.exception.EmpleadoNotFoundException;
import cl.innovatech.rrhh.exception.StrategyNotFoundException;
import cl.innovatech.rrhh.model.Empleado;
import cl.innovatech.rrhh.repository.EmpleadoRepository;
import cl.innovatech.rrhh.strategy.CapacityStrategy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final List<CapacityStrategy> capacityStrategies;

    public EmpleadoService(EmpleadoRepository empleadoRepository,
                           List<CapacityStrategy> capacityStrategies) {
        this.empleadoRepository = empleadoRepository;
        this.capacityStrategies = capacityStrategies;
    }

    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }

    public double calcularDisponibilidad(Long id) {
        Empleado empleado = this.buscarPorId(id); // Reutilizamos el buscador por ID

        String cargoEmpleado = empleado.getCargo().toUpperCase();

        return capacityStrategies.stream()
                .filter(s -> cargoEmpleado.contains(s.getCargoAsociado().toUpperCase()))
                .findFirst()
                .map(strategy -> strategy.calcularDisponibilidad(empleado))
                .orElseThrow(() -> new StrategyNotFoundException("No existe lógica de capacidad para el cargo: " + empleado.getCargo()));
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public Empleado actualizarEmpleado(Long id, Empleado empleadoActualizado) {
        Empleado empleadoExistente = buscarPorId(id);

        empleadoExistente.setNombre(empleadoActualizado.getNombre());
        empleadoExistente.setEmail(empleadoActualizado.getEmail());
        empleadoExistente.setCargo(empleadoActualizado.getCargo());
        empleadoExistente.setHorasAsignadas(empleadoActualizado.getHorasAsignadas());
        empleadoExistente.setCapacidadMaxima(empleadoActualizado.getCapacidadMaxima());
        return empleadoRepository.save(empleadoExistente);
    }

    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new EmpleadoNotFoundException(id);
        }
        empleadoRepository.deleteById(id);
    }



}