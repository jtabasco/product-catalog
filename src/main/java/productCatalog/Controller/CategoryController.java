/**
 * REST Controller for managing product categories.
 * Provides endpoints for CRUD operations on categories and their associated products.
 */
package productCatalog.Controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import productCatalog.Dto.CategoryDTO;
import productCatalog.Dto.CategoryListDTO;
import productCatalog.Entity.Category;
import productCatalog.Service.CategoryService;
import productCatalog.Exception.SuccessResponse;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    /** Service layer for category operations */
    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves all categories in a simplified format.
     * @return List of CategoryListDTO containing basic category information
     */
    @GetMapping
    public ResponseEntity<List<CategoryListDTO>> getAllCategories() {
        List<CategoryListDTO> categories = categoryService.getAllCategories().stream()
            .map(this::convertToListDTO)
            .collect(Collectors.toList());
        return ResponseEntity.status(200).body(categories);
    }

    /**
     * Retrieves a specific category by its ID with full details.
     * @param id The ID of the category to retrieve
     * @return CategoryDTO containing detailed category information
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(convertToDTO(categoryService.getCategoryById(id)));
    }

    /**
     * Creates a new category.
     * @param category The category entity to create
     * @return CategoryDTO of the created category
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
        CategoryDTO createdCategory = convertToDTO(categoryService.createCategory(category));
        return ResponseEntity.status(201).body(createdCategory);
    }

    /**
     * Updates an existing category.
     * @param id The ID of the category to update
     * @param category The updated category information
     * @return CategoryDTO of the updated category
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        CategoryDTO updatedCategory = convertToDTO(categoryService.updateCategory(id, category));
        return ResponseEntity.status(200).body(updatedCategory);
    }

    /**
     * Deletes a category by its ID.
     * @param id The ID of the category to delete
     * @param request The HTTP request for URI information
     * @return SuccessResponse indicating successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteCategory(
            @PathVariable Long id,
            jakarta.servlet.http.HttpServletRequest request) {
        categoryService.deleteCategory(id);
        // Return a success response with the URI of the request
        return  ResponseEntity.status(200).body(new SuccessResponse(
            String.format("Category with id : '%d' was successfully deleted", id),
            request.getRequestURI()
        ));
    }

    /**
     * Converts a Category entity to a detailed DTO including associated products.
     * @param category The category entity to convert
     * @return CategoryDTO with full details
     */
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getCategory_Id());
        dto.setName(category.getName());
        
        // Convert associated products to simplified DTOs
        dto.setProducts(category.getProducts().stream()
            .map(product -> {
                CategoryDTO.ProductInfo productInfo = new CategoryDTO.ProductInfo();
                productInfo.setId(product.getProduct_Id());
                productInfo.setName(product.getName());
                productInfo.setDescription(product.getDescription());
                return productInfo;
            })
            .collect(Collectors.toList()));
            
        return dto;
    }

    /**
     * Converts a Category entity to a simplified list DTO.
     * @param category The category entity to convert
     * @return CategoryListDTO with basic information
     */
    private CategoryListDTO convertToListDTO(Category category) {
        CategoryListDTO dto = new CategoryListDTO();
        dto.setId(category.getCategory_Id());
        dto.setName(category.getName());
        return dto;
    }
}
