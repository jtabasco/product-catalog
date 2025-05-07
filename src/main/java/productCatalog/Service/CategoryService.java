/**
 * Service interface for category-related business logic.
 * Defines the contract for category management operations.
 */
package productCatalog.Service;

import java.util.List;
import productCatalog.Entity.Category;

public interface CategoryService {
    /**
     * Retrieves all categories from the database.
     * @return List of all categories
     */
    List<Category> getAllCategories();

    /**
     * Retrieves a specific category by its ID.
     * @param id The ID of the category to retrieve
     * @return The requested category
     */
    Category getCategoryById(Long id);

    /**
     * Creates a new category in the database.
     * @param category The category to create
     * @return The created category
     */
    Category createCategory(Category category);

    /**
     * Updates an existing category.
     * @param id The ID of the category to update
     * @param category The updated category information
     * @return The updated category
     */
    Category updateCategory(Long id, Category category);

    /**
     * Deletes a category from the database.
     * @param id The ID of the category to delete
     */
    void deleteCategory(Long id);
}
