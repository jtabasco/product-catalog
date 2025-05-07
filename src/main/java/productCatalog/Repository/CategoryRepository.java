/**
 * Repository interface for Category entities.
 * Extends JpaRepository to provide CRUD operations and JPA functionality
 * for the Category entity.
 */
package productCatalog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import productCatalog.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Inherits standard CRUD operations from JpaRepository
    // Custom query methods can be added here if needed
}
