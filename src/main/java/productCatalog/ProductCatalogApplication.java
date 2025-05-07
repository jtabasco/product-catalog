/**
 * Main application class for the Product Catalog API.
 * This class serves as the entry point for the Spring Boot application.
 */
package productCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application class that enables auto-configuration and component scanning.
 * The @SpringBootApplication annotation combines:
 * - @Configuration: Marks this class as a source of bean definitions
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration mechanism
 * - @ComponentScan: Enables component scanning in the package
 */
@SpringBootApplication
public class ProductCatalogApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

}
