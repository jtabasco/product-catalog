/**
 * Standardized success response format for the API.
 * This class provides a consistent structure for successful operation responses.
 */
package productCatalog.Exception;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SuccessResponse {
    /** Timestamp when the operation completed */
    private LocalDateTime timestamp;
    
    /** HTTP status code (defaults to 200) */
    private int status;
    
    /** Success message describing the operation */
    private String message;
    
    /** API endpoint path where the operation was performed */
    private String path;

    /**
     * Constructs a new SuccessResponse with the specified details.
     * @param message Success message describing the operation
     * @param path API endpoint path where the operation was performed
     */
    public SuccessResponse(String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = 200;
        this.message = message;
        this.path = path;
    }
} 