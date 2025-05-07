/**
 * Data Transfer Object (DTO) for Product information with review statistics.
 * This class extends the basic product information with review-related data
 * including average rating and total number of reviews.
 */
package productCatalog.Dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class ProductReviewsDTO {
    /** Unique identifier of the product */
    private Long id;
    
    /** Name of the product */
    private String name;
    
    /** Description of the product */
    private String description;
    
    /** Price of the product */
    private BigDecimal price;
    
    /** Average rating of the product's reviews */
    private Double averageRating;
    
    /** Total number of reviews for the product */
    private Integer totalReviews;
    
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