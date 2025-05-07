/**
 * Global exception handler for the application.
 * This class provides centralized exception handling for all controllers,
 * ensuring consistent error responses across the API.
 */
package productCatalog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;



@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException by returning a 404 Not Found response.
     * @param ex The exception that was thrown
     * @param request The HTTP request that caused the exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, 
            HttpServletRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DataIntegrityException by returning a 409 Conflict response.
     * @param ex The exception that was thrown
     * @param request The HTTP request that caused the exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityException(
            DataIntegrityException ex, 
            HttpServletRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            "Conflict",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles validation errors by returning a 400 Bad Request response.
     * @param ex The validation exception that was thrown
     * @param request The HTTP request that caused the exception
     * @return ResponseEntity containing the validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        
        String message = Optional.ofNullable(ex.getBindingResult().getFieldError())
            .map(error -> String.format("Invalid value '%s' for %s: %s", 
                error.getRejectedValue(), 
                error.getField(), 
                error.getDefaultMessage()))
            .orElse("Validation failed");
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            message,
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentException by returning a 400 Bad Request response.
     * @param ex The exception that was thrown
     * @param request The HTTP request that caused the exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, 
            HttpServletRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions by returning a 500 Internal Server Error response.
     * This is a catch-all handler for unexpected exceptions.
     * @param ex The exception that was thrown
     * @param request The HTTP request that caused the exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, 
            HttpServletRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 