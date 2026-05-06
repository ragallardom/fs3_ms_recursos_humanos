package cl.innovatech.rrhh.repository;

import cl.innovatech.rrhh.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
