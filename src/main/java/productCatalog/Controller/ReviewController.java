/**
 * REST Controller for managing product reviews.
 * Provides endpoints for creating, retrieving, and deleting reviews,
 * along with review statistics for products.
 */
package productCatalog.Controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import productCatalog.Dto.ProductReviewsDTO;
import productCatalog.Dto.ReviewResponseDTO;
import productCatalog.Entity.Review;
import productCatalog.Entity.Product;
import productCatalog.Service.ReviewService;
import productCatalog.Service.ProductService;
import productCatalog.Exception.SuccessResponse;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    /** Service layer for review operations */
    @Autowired
    private ReviewService reviewService;

    /** Service layer for product operations */
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all reviews for a specific product with review statistics.
     * @param productId The ID of the product
     * @return ProductReviewsDTO containing product details and review information
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductReviewsDTO> getProductReviews(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(200).body(convertToDTO(product));
    }

    /**
     * Creates a new review for a product.
     * @param productId The ID of the product to review
     * @param review The review entity to create
     * @return The created review entity
     */
    @PostMapping("/product/{productId}")
    public ResponseEntity<ReviewResponseDTO> createReview(
            @PathVariable Long productId,
            @Valid @RequestBody Review review) {
        Review createdReview = reviewService.createReview(productId, review);
        ReviewResponseDTO response = new ReviewResponseDTO();
        response.setReview_Id(createdReview.getReview_Id());
        response.setComment(createdReview.getComment());
        response.setRating(createdReview.getRating());
        response.setProductId(productId);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Updates an existing review.
     * @param id The ID of the review to update
     * @param review The updated review information
     * @return The updated review entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        ReviewResponseDTO response = new ReviewResponseDTO();
        response.setReview_Id(updatedReview.getReview_Id());
        response.setComment(updatedReview.getComment());
        response.setRating(updatedReview.getRating());
        response.setProductId(updatedReview.getProduct().getProduct_Id());
        return ResponseEntity.status(200).body(response);
    }

    /**
     * Deletes a review by its ID.
     * @param id The ID of the review to delete
     * @param request The HTTP request for URI information
     * @return SuccessResponse indicating successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteReview(
            @PathVariable Long id,
            jakarta.servlet.http.HttpServletRequest request) {
        reviewService.deleteReview(id);
        // Return a success response with the URI of the request
        return  ResponseEntity.status(200).body(new SuccessResponse(
            String.format("Review with id : '%d' was successfully deleted", id),
            request.getRequestURI()
        ));
    }

    /**
     * Converts a Product entity to a DTO including review statistics and details.
     * @param product The product entity to convert
     * @return ProductReviewsDTO with product details and review information
     */
    private ProductReviewsDTO convertToDTO(Product product) {
        ProductReviewsDTO dto = new ProductReviewsDTO();
        dto.setId(product.getProduct_Id());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        
        List<Review> reviews = product.getReviews();
        
        // Calculate average rating with one decimal precision
        if (!reviews.isEmpty()) {
            double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
            dto.setAverageRating(Math.round(averageRating * 10.0) / 10.0);
        } else {
            dto.setAverageRating(0.0);
        }
        
        // Set total number of reviews
        dto.setTotalReviews(reviews.size());
        
        // Convert reviews to simplified DTOs
        dto.setReviews(reviews.stream()
            .map(review -> {
                ProductReviewsDTO.ReviewInfo reviewInfo = new ProductReviewsDTO.ReviewInfo();
                reviewInfo.setId(review.getReview_Id());
                reviewInfo.setComment(review.getComment());
                reviewInfo.setRating(review.getRating());
                return reviewInfo;
            })
            .collect(Collectors.toList()));
            
        return dto;
    }
} 