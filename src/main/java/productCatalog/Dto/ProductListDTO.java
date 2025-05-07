/**
 * Data Transfer Object (DTO) for simplified Product information used in list views.
 * This class contains only essential product information needed for displaying
 * products in a list format, without the associated categories and reviews.
 */
package productCatalog.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductListDTO {
    /** Unique identifier of the product */
    private Long id;
    
    /** Name of the product */
    private String name;
    
    /** Brief description of the product */
    private String description;
    
    /** Price of the product */
    private BigDecimal price;
} 