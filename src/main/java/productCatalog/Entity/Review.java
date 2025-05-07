/**
 * Review entity class representing a product review in the catalog.
 * This class maps to the 'reviews' table in the database and maintains
 * a many-to-one relationship with products.
 */
package productCatalog.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@Table (name = "reviews")
@JsonIgnoreProperties({"product"})
public class Review {
	/**
	 * Unique identifier for the review
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long review_Id;
	
	/**
	 * Text content of the review
	 */
	@Column(nullable = false)
	private String comment;
	
	/**
	 * Rating value between 1 and 5
	 */
	@Min(1)
	@Max(5)
	private Integer rating;
	
	/**
	 * Product this review belongs to
	 * Uses a foreign key 'product_id' to maintain the relationship
	 */
	@ManyToOne
	@JoinColumn(name = "product_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnoreProperties("reviews")
	private Product product;

}
