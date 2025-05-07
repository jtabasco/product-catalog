/**
 * Data Transfer Object (DTO) for Product information.
 * This class is used to transfer product data between layers,
 * including basic product information, associated categories, and reviews.
 */
package productCatalog.Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ProductDTO {
    /** Unique identifier of the product */
    private Long id;
    
    /** Name of the product */
    private String name;
    
    /** Description of the product */
    private String description;
    
    /** Price of the product */
    private BigDecimal price;
    
    /** List of category names associated with the product */
    private List<String> categories = new ArrayList<>();
    
    /** List of reviews associated with the product */
    private List<ReviewInfo> reviews = new ArrayList<>();

    /**
     * Inner class representing review information.
     * Contains basic review details without the product reference
     * to avoid circular dependencies in JSON serialization.
     */
    @Data
    public static class ReviewInfo {
        /** Unique identifier of the review */
        private Long id;
        
        /** Text content of the review */
        private String comment;
        
        /** Rating value of the review */
        private Integer rating;
    }
}