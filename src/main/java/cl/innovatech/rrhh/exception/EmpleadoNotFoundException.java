package cl.innovatech.rrhh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmpleadoNotFoundException extends RuntimeException {

    public EmpleadoNotFoundException(Long id) {
        super("Empleado con ID " + id + " no encontrado");
    }

    public EmpleadoNotFoundException(String mensaje) {
        super(mensaje);
    }
}