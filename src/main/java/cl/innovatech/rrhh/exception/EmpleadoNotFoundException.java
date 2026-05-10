package cl.innovatech.rrhh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmpleadoNotFoundException extends RuntimeException {
    public EmpleadoNotFoundException(String message) {
        super(message);
    }
}