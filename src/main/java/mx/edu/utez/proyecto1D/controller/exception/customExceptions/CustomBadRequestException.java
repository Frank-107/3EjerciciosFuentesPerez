package mx.edu.utez.proyecto1D.controller.exception.customExceptions;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String message) {
        super(message);
    }
}
