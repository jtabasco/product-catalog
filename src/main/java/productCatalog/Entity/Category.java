/**
 * Category entity class representing a product category in the catalog.
 * This class maps to the 'categories' table in the database and maintains
 * a many-to-many relationship with products.
 */
package productCatalog.Entity;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
	/**
	 * Unique identifier for the category
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_Id;
	
	/**
	 * Name of the category
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * Set of products belonging to this category
	 * Mapped by the 'categories' field in the Product entity
	 */
	@ManyToMany(mappedBy = "categories")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnoreProperties("categories")
	private Set<Product> products = new HashSet<>();
}