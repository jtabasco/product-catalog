# 🛍️ Product Catalog API

## 📋 Overview
A robust REST API for managing a product catalog system built with Spring Boot. This system allows for product management, categorization, and customer reviews.

## ⚡ Features
- 📦 **Product Management**
  - Create, read, update, and delete products
  - Manage product categories
  - Price management
  - Product descriptions

- 🏷️ **Category System**
  - Hierarchical category management
  - Category assignment to products
  - Category validation

- ⭐ **Review System**
  - Customer product reviews
  - Rating system (1-5 stars)
  - Review management
  - Average rating calculation

## 🛠️ Technical Stack
- **Framework:** Spring Boot
- **Language:** Java
- **Database:** JPA/Hibernate
- **Documentation:** JavaDoc
- **API Design:** REST principles
- **Error Handling:** Global exception handling

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

### 📥 Installation
1. Clone the repository
```bash
git clone https://github.com/jtabasco/product-catalog.git
```

2. Navigate to project directory
```bash
cd product-catalog
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

## 📱 Postman Collection

### Importing the Collection
1. Open Postman
2. Click on the "Import" button in the top-left corner
3. Select the "File" tab
4. Click "Upload Files" and select the `Product Catalog API.postman_collection.json` file
5. Click "Import" to add the collection to your workspace

### Collection Structure
The collection is organized into three main folders:
- **Products**: Endpoints for product management
- **Categories**: Endpoints for category management
- **Reviews**: Endpoints for review management

Each request includes:
- Pre-configured headers
- Request body templates
- Test scripts for response validation
- Example responses

### Environment Setup
1. Create a new environment in Postman
2. Select this environment to use the API endpoints

### Using the Collection
- All endpoints are pre-configured with the base URL
- Test scripts are included for response validation
- Example request bodies are provided for POST and PUT requests
- Response examples are documented in the collection

## 🔗 API Endpoints

### Products
- `GET /products` - Get all products
- `GET /products/{id}` - Get product by ID
- `POST /products` - Create new product
- `PUT /products/{id}` - Update product
- `DELETE /products/{id}` - Delete product
- `POST /products/{id}/categories/{id}`  - Add Category to Product
- `DELETE /products/{id}/categories/{id}` - Remove Category from Product
- `PUT /products/{id}/categories` - Update Product Categories

### Categories
- `GET /categories` - Get all categories
- `GET /categories/{id}` - Get category by ID
- `POST /categories` - Create new category
- `PUT /categories/{id}` - Update category
- `DELETE /categories/{id}` - Delete category

### Reviews
- `GET /reviews/products/{id}` - Get Reviews by Product with Average
- `POST /reviews/products/{id}` - Add product review
- `PUT /reviews/{id}` - Update Review
- `DELETE /reviews/{id}` - Delete review

## 🔒 Error Handling
The API includes comprehensive error handling:
- `404` - Resource not found
- `400` - Bad request / Validation errors
- `409` - Conflict in operations
- `500` - Internal server error

## 📚 Documentation
Detailed API documentation is available through JavaDoc comments in the code. Each component includes:
- Purpose and functionality description
- Parameter details
- Return value specifications
- Exception scenarios

## 🧪 Testing
Run the tests using:
```bash
mvn test
```

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors
- Joel Tabasco

## 🙏 Acknowledgments
- Promineo Tech

## 🔢 JSON Examples

### Products

#### Create Product (POST /products)
```json
{
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop with RTX 3080",
  "price": 1499.99
}
```

#### Update Product (PUT /products/{id})
```json
{
  "name": "Gaming Laptop Pro",
  "description": "Updated: High-performance gaming laptop with RTX 3080 Ti",
  "price": 1699.99
}
```

#### Update Product Categories (PUT /products/{id}/categories)
```json
{
  "categoryIds": [1, 2, 3]
}
```

### Categories

#### Create Category (POST /categories)
```json
{
  "name": "Electronics"
}
```

#### Update Category (PUT /categories/{id})
```json
{
  "name": "Gaming Electronics"
}
```

### Reviews

#### Create Review (POST /reviews/products/{id})
```json
{
  "rating": 5,
  "comment": "Excellent product! The performance exceeds expectations."
}
```

### Example Responses

#### Get Product Response (GET /products/{id})
```json
{
    "id": 1,
     "name": "Gaming Laptop",
  "description": "High-performance gaming laptop with RTX 3080",
  "price": 1499.99,
    "categories": [
        "Gaming",
        "Electronic"
    ],
    "reviews": [
        {
            "id": 1,
            "comment": "Great product!",
            "rating": 5
        },
        {
            "id": 4,
            "comment": "Great product!",
            "rating": 4
        }
    ]
}
```

#### Get Reviews by Product Response (GET /reviews/products/{id})
```json
{
  "productId": 1,
  "productName": "Gaming Laptop",
  "averageRating": 4.5,
  "totalReviews": 10,
  "reviews": [
    {
      "id": 1,
      "rating": 5,
      "comment": "Excellent product!"
    },
    {
      "id": 2,
      "rating": 4,
      "comment": "Good value for money"
    }
  ]
}
```

#### Error Response Example
```json
{
  "timestamp": "2024-03-15T10:30:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Product with id '999' not found",
  "path": "/products/999"
}
```