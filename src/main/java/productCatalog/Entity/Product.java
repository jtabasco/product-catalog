/**
 * Product entity class representing a product in the catalog.
 * This class maps to the 'products' table in the database and includes
 * relationships with reviews and categories.
 */
package productCatalog.Entity;

import java.math.BigDecimal;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
	/**
	 * Unique identifier for the product
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_Id;
	
	/**
	 * Name of the product
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * Detailed description of the product
	 */
	@Column(nullable = false)
	private String description;
	
	/**
	 * Price of the product
	 */
	@Column(nullable = false)
	private BigDecimal price;
	
	/**
	 * List of reviews associated with this product
	 * Uses CascadeType.ALL to propagate all operations to reviews
	 */
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("product")
	private List<Review> reviews = new ArrayList<>();

	/**
	 * Set of categories this product belongs to
	 * Uses a join table 'product_category' to manage the many-to-many relationship
	 */
	@ManyToMany
	@JoinTable(
		name = "product_category",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnoreProperties("products")
	private Set<Category> categories = new HashSet<>(); 
	
}