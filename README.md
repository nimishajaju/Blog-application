Blog Application — Spring Boot Backend

A backend-focused blog application built using Java, Spring Boot, MySQL, and REST APIs.

The application allows users to securely create, update, delete, and manage blog posts with image upload support, comments functionality, JWT authentication, role-based authorization, pagination, and API-based communication.

This project was developed to practice real-world backend development concepts such as authentication, authorization, database relationships, REST API development, pagination, image handling, and layered backend architecture.

Features
User authentication and authorization using Spring Security
JWT-based authentication and secure APIs
Role-based access control for users and admins
Create, update, delete, and manage blog posts
Upload and manage images for blog posts
Add and manage comments on blog posts
Pagination and sorting support
RESTful API architecture
MySQL database integration
Global exception handling
Request validation
Layered backend architecture
Tech Stack
Backend
Java
Spring Boot
Spring Security
Spring Data JPA
Hibernate
REST APIs
Database
MySQL
Tools
Git
GitHub
IntelliJ IDEA
Postman
Database Design

The project uses relational database mapping with JPA and Hibernate.

Implemented entity relationships such as:

One-to-Many relationship between Posts and Comments
Many-to-One relationship between Comments and Users
One-to-Many relationship between Users and Posts

These relationships were managed using JPA/Hibernate annotations for efficient database handling and entity mapping.

Project Structure
src/main/java/com/blogapplication
│
├── controller     # Handles API requests
├── service        # Business logic layer
├── repository     # Database interaction layer
├── model          # Entity classes
├── security       # JWT & Spring Security configuration
├── dto            # Data Transfer Objects
├── exception      # Exception handling
└── config         # Application configuration
API Features
Secure login and signup APIs
CRUD operations for blog posts
CRUD operations for comments
Protected APIs using JWT authentication
Pagination and sorting support
Image upload support
Role-based authorization
How to Run the Project
Prerequisites
JDK 17+
MySQL
Maven
IntelliJ IDEA / VS Code
Clone Repository
git clone https://github.com/nimishajaju/Blog-application.git
Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=your_username
spring.datasource.password=your_password
Run Application
mvn spring-boot:run

Application runs on:

http://localhost:8080
Learning Outcomes

Through this project, I learned:

Backend development using Spring Boot
Spring Security and JWT authentication
REST API design and development
Pagination and sorting
File and image handling
Database integration using MySQL
JPA/Hibernate entity relationships
One-to-Many and Many-to-One mapping
Exception handling
Layered architecture implementation
Git and GitHub workflow
Future Improvements
Docker deployment
API documentation using Swagger
Cloud deployment using AWS
Redis caching
Email notification system
Like and reply functionality for comments
Author
Nimisha Jaju

Aspiring Backend Developer transitioning from a fashion design background into software development.
