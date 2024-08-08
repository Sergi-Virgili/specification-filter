
# Project Demo: Filtering with JPA Specifications

This project demonstrates how to use JPA Specifications to filter and paginate data in a Spring Boot application. The application includes examples of how to create dynamic queries based on various criteria, and how to sort the results.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Configuration](#configuration)

## Introduction

This project serves as a demonstration of using JPA Specifications in a Spring Boot application to create dynamic queries. It includes filtering, pagination, and sorting capabilities, which are essential for building robust and flexible APIs.

## Features

- Dynamic filtering based on multiple criteria
- Pagination and sorting of query results
- Integration with H2 in-memory database for development and testing
- Comprehensive API documentation using Swagger

## Technologies Used

- Java 21
- Spring Boot 3.3.2
- Spring Data JPA
- H2 Database
- Swagger/OpenAPI for API documentation
- Maven for build and dependency management

## Setup and Installation

1. **Clone or fork the repository:**


2. **Build the project:**

   ```sh
   mvn clean install
   ```

3. **Run the application:**

   ```sh
   mvn spring-boot:run
   ```

4. **Access the application:**
   - API Documentation: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Usage

The application provides endpoints to filter, paginate, and sort `Project` entities based on various criteria such as name, manager, location, start date, and end date.

### Example Request

```http
GET /projects?page=0&size=10&sortBy=startDate&order=desc
```

### API Endpoints

- **Get Projects:**

  ```http
  GET /projects
  ```

  **Query Parameters:**

  - `name` (Optional): Filter by project name
  - `manager` (Optional): Filter by project manager
  - `location` (Optional): Filter by project location
  - `startDate` (Optional): Filter by start date (ISO format)
  - `endDate` (Optional): Filter by end date (ISO format)
  - `sortBy` (Optional): Sort by field (e.g., `startDate`)
  - `order` (Optional): Sort order (`asc` or `desc`)
  - `page` (Optional): Page number
  - `size` (Optional): Page size

## Testing

The project includes tests to ensure that the filtering, pagination, and sorting functionalities work correctly.

To run the tests:

```sh
mvn test
```

## Configuration

The application uses different configurations for development and testing environments to ensure proper database isolation.

- **Development Configuration:** `src/main/resources/application-dev.properties`
- **Test Configuration:** `src/main/resources/application-test.properties`

```properties
# Development database configuration
spring.datasource.url=jdbc:h2:mem:devdb
spring.jpa.hibernate.ddl-auto=create
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always

# Test database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=never
```