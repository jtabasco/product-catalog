/**
 * Repository interface for Review entities.
 * Extends JpaRepository to provide CRUD operations and JPA functionality
 * for the Review entity.
 */
package productCatalog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import productCatalog.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Inherits standard CRUD operations from JpaRepository
    // Custom query methods can be added here if needed
}
