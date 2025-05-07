/**
 * Data Transfer Object (DTO) for simplified Category information used in list views.
 * This class contains only essential category information needed for displaying
 * categories in a list format, without the associated products.
 */
package productCatalog.Dto;

import lombok.Data;

@Data
public class CategoryListDTO {
    /** Unique identifier of the category */
    private Long id;
    
    /** Name of the category */
    private String name;
} 