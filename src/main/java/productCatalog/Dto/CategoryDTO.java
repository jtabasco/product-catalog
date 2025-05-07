/**
 * Data Transfer Object (DTO) for Category information.
 * This class is used to transfer category data between layers,
 * including basic category information and associated products.
 */
package productCatalog.Dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CategoryDTO {
    /** Unique identifier of the category */
    private Long id;
    
    /** Name of the category */
    private String name;
    
    /** List of products associated with the category */
    private List<ProductInfo> products = new ArrayList<>();

    /**
     * Inner class representing product information.
     * Contains basic product details without the category reference
     * to avoid circular dependencies in JSON serialization.
     */
    @Data
    public static class ProductInfo {
        /** Unique identifier of the product */
        private Long id;
        
        /** Name of the product */
        private String name;
        
        /** Description of the product */
        private String description;
    }
} 