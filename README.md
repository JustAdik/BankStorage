BankStorage

BankStorage is a banking web application developed with Java and Spring Boot. The project simulates core banking operations such as account management, balance handling, money transfers, and transaction tracking while following modern backend development practices.

Features
User registration and authentication
Secure login with Spring Security
Role-based access control (USER / ADMIN)
Account balance management
Money transfers between users
Transaction history tracking
PostgreSQL database integration
RESTful architecture
Layered architecture (Controller → Service → Repository)
Tech Stack
Backend
Java 21
Spring Boot
Spring Security
Spring Data JPA
Hibernate
Database
PostgreSQL
Build Tool
Maven
Frontend
Thymeleaf
HTML
CSS
Bootstrap
Project Architecture

Controller Layer

Handles HTTP requests and responses.

Service Layer

Contains business logic and banking operations.

Repository Layer

Communicates with PostgreSQL using Spring Data JPA.

Database Layer

Stores users, balances, and transaction history.
Main Functionality
Authentication
User registration
User login
Secure password storage
Spring Security authorization
Banking Operations
View account information
Manage account balance
Transfer money to another account
View transaction history
Administration
Role-based access control
Separate permissions for administrators and users
Database

The application uses PostgreSQL for persistent data storage.

Main entities:

BankUser
Transaction
Future Improvements
Docker support
Swagger/OpenAPI documentation
Email notifications
Account statements
Currency conversion
Microservice architecture
Kafka integration
Screenshots

Add screenshots here:

Login Page




Main Dashboard




Money Transfer




Transaction History




Getting Started
Clone Repository
git clone https://github.com/JustAdik/BankStorage.git
Configure Database

Create PostgreSQL database:

CREATE DATABASE BankStorage;

Update application.properties with your database credentials.

Run Application
mvn spring-boot:run

Application will be available at:

http://localhost:8080
Author

Adilet Eraliev

GitHub:
https://github.com/JustAdik
