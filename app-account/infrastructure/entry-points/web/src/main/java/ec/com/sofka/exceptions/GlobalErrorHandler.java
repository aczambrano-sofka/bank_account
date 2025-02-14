package ec.com.sofka.exceptions;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import ec.com.sofka.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalErrorHandler {

    // Manejo de MismatchedInputException para errores en la deserialización de JSON
    @ExceptionHandler(MismatchedInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMismatchedInput(MismatchedInputException ex) {
        String message = "Invalid or missing fields in JSON request";
        return createErrorResponse(ex, HttpStatus.BAD_REQUEST, message);
    }

    // Manejo de NoSuchElementException (cuando no se encuentra un registro)
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoSuchElement(NoSuchElementException ex) {
        return createErrorResponse(ex, HttpStatus.NOT_FOUND, "Resource not found: " + ex.getMessage());
    }
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex) {
        return createErrorResponse(ex, HttpStatus.CONFLICT, ex.getMessage());
    }
    // Manejo de IllegalArgumentException (cuando hay un argumento incorrecto)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return createErrorResponse(ex, HttpStatus.BAD_REQUEST, "Invalid argument: " + ex.getMessage());
    }

    // Manejo de NullPointerException (cuando hay una referencia nula)
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException ex) {
        return createErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Null pointer encountered: " + ex.getMessage());
    }

    // Manejo de todas las excepciones RuntimeException (para errores generales)
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return createErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred: " + ex.getMessage());
    }

    // Manejo de excepciones generales (catch-all para errores no especificados)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return createErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: " + ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(Throwable ex, HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
