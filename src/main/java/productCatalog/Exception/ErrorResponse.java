/**
 * Standardized error response format for the API.
 * This class provides a consistent structure for error responses across all endpoints.
 */
package productCatalog.Exception;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorResponse {
    /** Timestamp when the error occurred */
    private LocalDateTime timestamp;
    
    /** HTTP status code of the error */
    private int status;
    
    /** Type of error (e.g., "Not Found", "Bad Request") */
    private String error;
    
    /** Detailed error message */
    private String message;
    
    /** API endpoint path where the error occurred */
    private String path;

    /**
     * Constructs a new ErrorResponse with the specified details.
     * @param status HTTP status code
     * @param error Type of error
     * @param message Detailed error message
     * @param path API endpoint path
     */
    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
} 