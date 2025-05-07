/**
 * REST Controller for managing products in the catalog.
 * Provides endpoints for CRUD operations on products and their relationships with categories.
 */
package productCatalog.Controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import productCatalog.Dto.ProductDTO;
import productCatalog.Dto.ProductListDTO;
import productCatalog.Entity.Product;
import productCatalog.Entity.Category;
import productCatalog.Service.ProductService;
import productCatalog.Exception.SuccessResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

    /** Service layer for product operations */
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products in a simplified format.
     * @return List of ProductListDTO containing basic product information
     */
    @GetMapping
    public ResponseEntity<List<ProductListDTO>> getAllProducts() {
        List<ProductListDTO> products = productService.getAllProducts().stream()
            .map(this::convertToListDTO)
            .collect(Collectors.toList());
        return ResponseEntity.status(200).body(products);
    }

    /**
     * Retrieves a specific product by its ID with full details.
     * @param id The ID of the product to retrieve
     * @return ProductDTO containing detailed product information
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(convertToDTO(productService.getProductById(id)));
    }

    /**
     * Creates a new product.
     * @param product The product entity to create
     * @return ProductDTO of the created product
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody Product product) {
        ProductDTO createdProduct = convertToDTO(productService.createProduct(product));
        return ResponseEntity.status(201).body(createdProduct);
    }

    /**
     * Updates an existing product.
     * @param id The ID of the product to update
     * @param product The updated product information
     * @return ProductDTO of the updated product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        ProductDTO updatedProduct = convertToDTO(productService.updateProduct(id, product));
        return ResponseEntity.status(200).body(updatedProduct);
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete
     * @param request The HTTP request for URI information
     * @return SuccessResponse indicating successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteProduct(@PathVariable Long id, jakarta.servlet.http.HttpServletRequest request) {
        productService.deleteProduct(id);
        // Return a success response with the URI of the request
        return ResponseEntity.status(200).body(new SuccessResponse(
            String.format("Product with id : '%d' was successfully deleted", id),
            request.getRequestURI()
        ));
    }

    /**
     * Adds a category to a product.
     * @param id The ID of the product
     * @param categoryId The ID of the category to add
     * @return ProductDTO of the updated product
     */
    @PostMapping("/{id}/categories/{categoryId}")
    public ResponseEntity<ProductDTO> addCategoryToProduct(@PathVariable Long id, @PathVariable Long categoryId) {
        ProductDTO updatedProduct = convertToDTO(productService.addCategoryToProduct(id, categoryId));
        return ResponseEntity.status(201).body(updatedProduct);
    }

    /**
     * Removes a category from a product.
     * @param id The ID of the product
     * @param categoryId The ID of the category to remove
     * @param request The HTTP request for URI information
     * @return SuccessResponse indicating successful removal
     */
    @DeleteMapping("/{id}/categories/{categoryId}")
    public ResponseEntity<SuccessResponse> removeCategoryFromProduct(
            @PathVariable Long id, 
            @PathVariable Long categoryId,
            jakarta.servlet.http.HttpServletRequest request) {
        productService.removeCategoryFromProduct(id, categoryId);
        // Return a success response with the URI of the request
        return ResponseEntity.status(200).body(new SuccessResponse(
            String.format("Category with id : '%d' was successfully removed from Product with id : '%d'", categoryId, id),
            request.getRequestURI()
        ));
    }

    /**
     * Updates all categories for a product.
     * @param id The ID of the product
     * @param categoryIds List of category IDs to associate with the product
     * @return ProductDTO of the updated product
     */
    @PutMapping("/{id}/categories")
    public ResponseEntity<ProductDTO> updateProductCategories(@PathVariable Long id, @RequestBody List<Long> categoryIds) {
        ProductDTO updatedProduct = convertToDTO(productService.updateProductCategories(id, categoryIds));
        return ResponseEntity.status(200).body(updatedProduct);
    }

    /**
     * Converts a Product entity to a detailed DTO including associated categories and reviews.
     * @param product The product entity to convert
     * @return ProductDTO with full details
     */
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getProduct_Id());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        
        // Convert associated categories to names
        dto.setCategories(product.getCategories().stream()
            .map(Category::getName)
            .collect(Collectors.toList()));
        
        // Convert associated reviews to simplified DTOs
        dto.setReviews(product.getReviews().stream()
            .map(review -> {
                ProductDTO.ReviewInfo reviewInfo = new ProductDTO.ReviewInfo();
                reviewInfo.setId(review.getReview_Id());
                reviewInfo.setComment(review.getComment());
                reviewInfo.setRating(review.getRating());
                return reviewInfo;
            })
            .collect(Collectors.toList()));
            
        return dto;
    }

    /**
     * Converts a Product entity to a simplified list DTO.
     * @param product The product entity to convert
     * @return ProductListDTO with basic information
     */
    private ProductListDTO convertToListDTO(Product product) {
        ProductListDTO dto = new ProductListDTO();
        dto.setId(product.getProduct_Id());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
