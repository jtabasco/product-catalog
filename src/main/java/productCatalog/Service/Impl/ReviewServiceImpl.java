/**
 * Implementation of the ReviewService interface.
 * Provides business logic for review management operations.
 */
package productCatalog.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import productCatalog.Entity.Review;
import productCatalog.Entity.Product;
import productCatalog.Exception.ResourceNotFoundException;
import productCatalog.Repository.ReviewRepository;
import productCatalog.Repository.ProductRepository;
import productCatalog.Service.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    /** Repository for review data access */
    @Autowired
    private ReviewRepository reviewRepository;

    /** Repository for product data access */
    @Autowired
    private ProductRepository productRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        return product.getReviews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review createReview(Long productId, Review review) {
        validateReview(review);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        review.setProduct(product);
        return reviewRepository.save(review);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review updateReview(Long id, Review reviewDetails) {
        Review existingReview = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        validateReview(reviewDetails);

        existingReview.setComment(reviewDetails.getComment());
        existingReview.setRating(reviewDetails.getRating());

        return reviewRepository.save(existingReview);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        reviewRepository.delete(review);
    }

    /**
     * Validates review data before saving.
     * Ensures that the rating is between 1 and 5 stars and the comment is not empty.
     * 
     * @param review The review to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateReview(Review review) {
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars");
        }
        if (review.getComment() == null || review.getComment().trim().isEmpty()) {
            throw new IllegalArgumentException("Review comment cannot be empty");
        }
    }
} 