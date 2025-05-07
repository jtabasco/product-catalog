/**
 * Repository interface for Product entities.
 * Extends JpaRepository to provide CRUD operations and JPA functionality
 * for the Product entity.
 */
package productCatalog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import productCatalog.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Inherits standard CRUD operations from JpaRepository
    // Custom query methods can be added here if needed
}
