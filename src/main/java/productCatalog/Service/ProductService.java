/**
 * Service interface for product-related business logic.
 * Defines the contract for product management operations.
 */
package productCatalog.Service;

import java.util.List;

import productCatalog.Entity.Product;

public interface ProductService {
    /**
     * Retrieves all products from the database.
     * @return List of all products
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a specific product by its ID.
     * @param id The ID of the product to retrieve
     * @return The requested product
     */
    Product getProductById(Long id);

    /**
     * Creates a new product in the database.
     * @param product The product to create
     * @return The created product
     */
    Product createProduct(Product product);

    /**
     * Updates an existing product.
     * @param id The ID of the product to update
     * @param product The updated product information
     * @return The updated product
     */
    Product updateProduct(Long id, Product product);

    /**
     * Deletes a product from the database.
     * @param id The ID of the product to delete
     */
    void deleteProduct(Long id);

    /**
     * Adds a category to a product.
     * @param id The ID of the product
     * @param categoryId The ID of the category to add
     * @return The updated product
     */
    Product addCategoryToProduct(Long id, Long categoryId);

    /**
     * Removes a category from a product.
     * @param id The ID of the product
     * @param categoryId The ID of the category to remove
     * @return The updated product
     */
    Product removeCategoryFromProduct(Long id, Long categoryId);

    /**
     * Updates all categories for a product.
     * @param id The ID of the product
     * @param categoryIds List of category IDs to associate with the product
     * @return The updated product
     */
    Product updateProductCategories(Long id, List<Long> categoryIds);
}
