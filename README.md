# BankStorage

BankStorage is a simple web banking application built with Spring Boot. The project demonstrates user registration, authentication, personal bank accounts, balance top-up, money transfers, and transaction history.

## Features

- User registration and login
- Password hashing with Spring Security
- Personal account number generation
- Balance view and balance top-up
- Money transfers between users
- Transaction history for sent and received transfers
- Server-side pages rendered with Thymeleaf
- PostgreSQL persistence with Spring Data JPA

## Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Maven

## Project Structure

```text
BankStorage/
├── src/main/java/com/example/BankStorage/
│   ├── controller/      # Web controllers
│   ├── model/           # JPA entities
│   ├── repository/      # Spring Data repositories
│   ├── security/        # Security configuration
│   └── service/         # Business logic
├── src/main/resources/
│   ├── templates/       # Thymeleaf pages
│   └── application.properties
├── pom.xml
└── mvnw / mvnw.cmd
```

## Getting Started

### Prerequisites

Install:

- Java 17+
- PostgreSQL
- Maven, or use the included Maven Wrapper

### Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE bankstorage;
```

Then update `BankStorage/src/main/resources/application.properties` with your local PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bankstorage
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`, so the required tables are created automatically when the application starts.

### Run the Application

From the repository root:

```bash
cd BankStorage
./mvnw spring-boot:run
```

On Windows:

```powershell
cd BankStorage
.\mvnw.cmd spring-boot:run
```

Open the app in your browser:

```text
http://localhost:8080
```

## Main Pages

- `/register` - create a new user account
- `/login` - sign in
- `/bank` - view account information and balance
- `/transfer` - transfer money to another account
- `/history` - view transaction history

## Notes

This project is intended for learning Spring Boot, MVC, Security, JPA, and basic banking logic. It is not production-ready banking software.
