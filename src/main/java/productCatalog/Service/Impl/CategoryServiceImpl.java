/**
 * Implementation of the CategoryService interface.
 * Provides business logic for category management operations.
 */
package productCatalog.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import productCatalog.Entity.Category;
import productCatalog.Exception.ResourceNotFoundException;
import productCatalog.Exception.DataIntegrityException;
import productCatalog.Repository.CategoryRepository;
import productCatalog.Service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    /** Repository for category data access */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category createCategory(Category category) {
        validateCategory(category);
        return categoryRepository.save(category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = getCategoryById(id);
        validateCategory(category);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    /**
     * {@inheritDoc}
     * @throws DataIntegrityException if the category has associated products
     */
    @Override
    public void deleteCategory(Long id) {
        Category existingCategory = getCategoryById(id);

        if (!existingCategory.getProducts().isEmpty()) {
            throw new DataIntegrityException(
                "Category",
                "id",
                id,
                "it has associated products"
            );
        }

        categoryRepository.delete(existingCategory);
    }

    /**
     * Validates category data before saving.
     * @param category The category to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
    }
}