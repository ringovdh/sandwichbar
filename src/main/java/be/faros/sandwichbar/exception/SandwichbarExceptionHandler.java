package be.faros.sandwichbar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SandwichbarExceptionHandler {

    @ExceptionHandler(InvalidOrderException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<SandwichbarErrorResponse> handleInvalidOrderException(InvalidOrderException ex) {
        return new ResponseEntity<>(new SandwichbarErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        ), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<SandwichbarErrorResponse> handleInvalidUserException(InvalidUserException ex) {
        return new ResponseEntity<>(new SandwichbarErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        ), HttpStatus.CONFLICT);
    }
}
