package cl.innovatech.rrhh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class StrategyNotFoundException extends RuntimeException {
    public StrategyNotFoundException(String message) {
        super(message);
    }
}