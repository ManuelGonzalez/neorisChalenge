# User Management System

## Description

This Spring Boot application provides a RESTful User Management System. It offers functionalities to create, retrieve, update, and delete user information, ensuring data validation and persistence using an in-memory H2 database. The application is also equipped with Swagger for API documentation and easy testing.

## Features

- CRUD operations for user management.
- RESTful API endpoints.
- Custom validations for email and password formats.
- H2 in-memory database for quick setup and testing.
- Swagger UI integration for API documentation and exploration.

## System Requirements

- Java JDK 11 or later
- Maven 3.6+ or Gradle 6.0+ (depending on your build tool preference)
- An IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code

## Installation & Setup

1. **Clone the Repository**

   ```shell
   git https://github.com/ManuelGonzalez/neorisChalenge
   cd neorisChalenge

2. **Build the Project**

    ```shell
    mvn clean install

3. **Run the Application**

    ```shell
    java -jar target/user-management-system-0.0.1-SNAPSHOT.jar

Or run the application directly from your IDE.

Usage
The application exposes various RESTful endpoints:

- Create User: POST /api/users
- Get User by ID: GET /api/users/{id}
- Update User: PUT /api/users/{id}
- Delete User: DELETE /api/users/{id}
- List All Users: GET /api/users

Access the Swagger UI at: http://localhost:8080/swagger-ui.html

**API Documentation**

The Swagger-generated API documentation is available at http://localhost:8080/v2/api-docs and through the Swagger UI for interactive exploration of the API.

**Architecture**

The application follows a typical Spring Boot architecture with a Controller-Service-Repository pattern:

- Controllers: Handle HTTP requests, invoking Service layer operations.
- Services: Contain business logic and interact with Repositories.
- Repositories: Interface with the database for data persistence.
- Entities/DTOs: Data models representing database tables and data transfer objects.