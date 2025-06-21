# Book Management API with JPA Examples

## Project Overview
This project consists of two main parts:

### 1. Book Management API
A RESTful API for managing books with Spring Security implementation.

#### Features:
- CRUD operations for books
- Validation for book attributes
- Basic Authentication with user/admin roles
- Swagger/OpenAPI documentation
- Error handling with meaningful HTTP status codes

#### Security:
- All `/books/**` endpoints require authentication
- DELETE operations require ADMIN role
- Two types of users:
  - Regular user (role: USER)
  - Admin user (role: ADMIN)

#### Book Entity Validation:
- Title: 1-100 characters, not null
- Author: 1-50 characters, letters/spaces/dots/hyphens only
- Published Year: Between 1500 and current year

### 2. One-to-Many Relationship Example
Located under `jpa_one2many` package, this section demonstrates JPA relationships and SQL queries.

#### Features:
- User-Order entities with One-to-Many relationship
- Custom SQL queries in repositories
- Dedicated REST controller for testing
- Example queries include:
  - Finding users with more than X orders
  - Calculating total order amount per user
  - Finding users with no orders

#### Endpoints:
Available under `/one2many-example/**` path:
- GET `/users/with-many-orders`
- GET `/users/order-summary`
- GET `/users/without-orders`

## Technical Stack
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- SpringDoc OpenAPI UI
- Maven

## Getting Started
1. Clone the repository
2. Run `mvn clean install`
3. Start the application
4. Access Swagger UI: `http://localhost:8080/swagger-ui.html`

## Authentication
- Regular User:
  - Username: user
  - Password: userpass
- Admin User:
  - Username: admin
  - Password: adminpass

## API Documentation
Detailed API documentation is available through Swagger UI when the application is running.
