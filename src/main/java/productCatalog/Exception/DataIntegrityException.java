/**
 * Custom exception class for handling data integrity violations.
 * This exception is thrown when attempting to perform an operation that would
 * violate database constraints or business rules, such as deleting a category
 * that still has associated products.
 */
package productCatalog.Exception;

public class DataIntegrityException extends RuntimeException {
    /**
     * Constructs a new DataIntegrityException with a detailed message.
     * @param resourceName The type of resource causing the integrity violation
     * @param fieldName The field used to identify the resource
     * @param fieldValue The value of the identifying field
     * @param reason The specific reason for the integrity violation
     */
    public DataIntegrityException(String resourceName, String fieldName, Object fieldValue, String reason) {
        super(String.format("Cannot delete %s with %s : '%s' because %s", resourceName, fieldName, fieldValue, reason));
    }
} 