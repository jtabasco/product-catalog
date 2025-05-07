/**
 * Implementation of the ProductService interface.
 * Provides business logic for product management operations including:
 * - Basic CRUD operations for products
 * - Category management for products
 * - Product validation
 */
package productCatalog.Service.Impl;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import productCatalog.Entity.Category;
import productCatalog.Entity.Product;
import productCatalog.Exception.ResourceNotFoundException;
import productCatalog.Repository.ProductRepository;
import productCatalog.Service.CategoryService;
import productCatalog.Service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    /** Repository for product data access operations */
    @Autowired
    private ProductRepository productRepository;

    /** Service for managing category operations */
    @Autowired
    private CategoryService categoryService;

    /**
     * {@inheritDoc}
     * Retrieves all products from the database without any filtering.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if the product data is invalid
     */
    @Override
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if the product to update is not found
     * @throws IllegalArgumentException if the updated product data is invalid
     */
    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = getProductById(id);
        validateProduct(productDetails);

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());

        return productRepository.save(existingProduct);
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if the product to delete is not found
     */
    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    /**
     * Validates product data before saving or updating.
     * Ensures that:
     * - Product name is not null or empty
     * - Product price is not null and is greater than or equal to zero
     *
     * @param product The product to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price must be greater than or equal to 0");
        }
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if either the product or category is not found
     */
    @Override
    public Product addCategoryToProduct(Long id, Long categoryId) {
        Product product = getProductById(id);
        Category category = categoryService.getCategoryById(categoryId);
        product.getCategories().add(category);
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if either:
     * - The product is not found
     * - The category is not found
     * - The category is not associated with the product
     */
    @Override
    public Product removeCategoryFromProduct(Long id, Long categoryId) {
        Product product = getProductById(id);
        Category category = categoryService.getCategoryById(categoryId);
        
        if (!product.getCategories().remove(category)) {
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }
        
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if either the product or any of the categories are not found
     */
    @Override
    public Product updateProductCategories(Long id, List<Long> categoryIds) {
        Product product = getProductById(id);
        Set<Category> newCategories = new HashSet<>();
        
        for (Long categoryId : categoryIds) {
            Category category = categoryService.getCategoryById(categoryId);
            newCategories.add(category);
        }
        
        product.setCategories(newCategories);
        return productRepository.save(product);
    }
} 