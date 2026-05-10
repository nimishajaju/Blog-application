# Blog Application - Spring Boot Backend

A backend-focused blog application built using Java, Spring Boot, MySQL, and REST APIs.

The application allows users to securely create, update, delete, and manage blog posts with image upload support, comments functionality, JWT authentication, role-based authorization, pagination, and API-based communication.

This project was developed to practice real-world backend development concepts such as authentication, authorization, database relationships, REST API development, pagination, image handling, and layered backend architecture.

---

# Features

- User authentication and authorization using Spring Security
- JWT-based authentication and secure APIs
- Role-based access control for users and admins
- Create, update, delete, and manage blog posts
- Upload and manage images for blog posts
- Add and manage comments on blog posts
- Pagination and sorting support
- RESTful API architecture
- MySQL database integration
- Global exception handling
- Request validation

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- REST APIs

## Database
- MySQL

## Tools
- Git
- GitHub
- IntelliJ IDEA
- Postman

---

# Database Design

The project uses relational database mapping with JPA and Hibernate.

Implemented entity relationships such as:
- One-to-Many relationship between Posts and Comments
- Many-to-One relationship between Comments and Users
- One-to-Many relationship between Users and Posts

These relationships were managed using JPA/Hibernate annotations for efficient database handling and entity mapping.

---
