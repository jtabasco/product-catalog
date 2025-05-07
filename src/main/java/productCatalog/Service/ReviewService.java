/**
 * Service interface for review-related business logic.
 * Defines the contract for review management operations.
 */
package productCatalog.Service;

import java.util.List;
import productCatalog.Entity.Review;

public interface ReviewService {
    /**
     * Retrieves all reviews for a specific product.
     * @param productId The ID of the product
     * @return List of reviews for the specified product
     */
    List<Review> getReviewsByProductId(Long productId);

    /**
     * Creates a new review for a product.
     * @param productId The ID of the product to review
     * @param review The review to create
     * @return The created review
     */
    Review createReview(Long productId, Review review);

    /**
     * Updates an existing review.
     * @param id The ID of the review to update
     * @param review The updated review information
     * @return The updated review
     */
    Review updateReview(Long id, Review review);

    /**
     * Deletes a review from the database.
     * @param id The ID of the review to delete
     */
    void deleteReview(Long id);
} 