/**
 * Custom exception class for handling cases where a requested resource is not found.
 * This exception is thrown when attempting to access a resource that doesn't exist
 * in the database, such as a non-existent product, category, or review.
 */
package productCatalog.Exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException with a detailed message.
     * @param resourceName The type of resource that was not found (e.g., "Product", "Category")
     * @param fieldName The field used to search for the resource (e.g., "id", "name")
     * @param fieldValue The value that was searched for
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
} 