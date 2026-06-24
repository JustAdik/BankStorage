# BankStorage

BankStorage is a banking web application developed with Java and Spring Boot. The project simulates core banking operations such as account management, balance handling, money transfers, and transaction tracking while following modern backend development practices.

## Features

- User registration and authentication
- Secure login with Spring Security
- Role-based access control (USER / ADMIN)
- Account balance management
- Money transfers between users
- Transaction history tracking
- PostgreSQL database integration
- RESTful architecture
- Layered architecture (Controller → Service → Repository)

## Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Maven
- Hibernate

## Screenshots

- Login Page
<img width="1600" height="860" alt="image" src="https://github.com/user-attachments/assets/57779d9d-541d-4f9e-a36b-868cbb9d8157" />

- Register Page
<img width="1600" height="860" alt="image" src="https://github.com/user-attachments/assets/c6099bf3-2d36-4e58-9040-c91ef443d36e" />

- Main Dashboard
<img width="1600" height="854" alt="image" src="https://github.com/user-attachments/assets/cb1f30be-be1f-4c66-9869-ed435e2a0c17" />

- Money Transfer
<img width="1600" height="856" alt="image" src="https://github.com/user-attachments/assets/5081bb79-be6a-4846-b010-dfbc42ea5506" />

- Transaction History
<img width="1600" height="867" alt="image" src="https://github.com/user-attachments/assets/d36f9768-ea81-4e00-8d4a-9a7585afa4e6" />

- Search user Page
<img width="1600" height="856" alt="image" src="https://github.com/user-attachments/assets/d88b4a5f-0062-48bd-9a02-3d8c2173fb26" />

- Chat page
<img width="1600" height="860" alt="image" src="https://github.com/user-attachments/assets/30072fef-57d8-436f-b625-f1e218951487" />




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
