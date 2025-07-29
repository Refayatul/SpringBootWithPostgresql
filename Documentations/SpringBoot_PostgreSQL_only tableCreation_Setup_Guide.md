
# Spring Boot + PostgreSQL Setup Guide 🚀
*A beginner-friendly guide to connecting Spring Boot with PostgreSQL using PGAdmin4.*

## Table of Contents 📖
- [Overview](#1-overview-ℹ️)
- [Prerequisites](#2-prerequisites-✔️)
- [Database Setup](#3-database-setup-in-pgadmin4-🛠️)
- [Spring Boot Configuration](#4-spring-boot-configuration-⚙️)
- [Person Entity Explained](#5-person-entity-explained-👤)
- [PersonRepo Repository](#6-personrepo-repository-📂)
- [How It Works Together](#7-how-it-works-together-🔄)
- [Testing & Verification](#8-testing--verification-✅)
- [Troubleshooting](#9-troubleshooting-🚨)
- [Next Steps](#10-next-steps-🔜)

## 1. Overview ℹ️
This guide walks through:
- Configuring a PostgreSQL database in PGAdmin4.
- Connecting Spring Boot using `application.properties`.
- Creating a Person entity (mapped to a database table).
- Using `PersonRepo` for CRUD operations.

## 2. Prerequisites ✔️
You’ll need:
- PostgreSQL installed.
- PGAdmin4 for database management.
- A Spring Boot project with these dependencies:

```xml
<dependencies>
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>
    <!-- Lombok (for @Data) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## 3. Database Setup in PGAdmin4 🛠️
**Create a Database:**
- Name: `rifat_db`
- Username: `postgres`
- Password: `123` (set during PostgreSQL install).

**Verify Connection:**
- Open PGAdmin4 → Connect to `localhost:5432`.
- Confirm `rifat_db` exists.

## 4. Spring Boot Configuration ⚙️

### `application.properties`
```properties
# App Info
spring.application.name=demo

# PostgreSQL Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/rifat_db
spring.datasource.username=postgres
spring.datasource.password=123

# Hibernate (Auto-update schema)
spring.jpa.hibernate.ddl-auto=update
```

| Property | Purpose |
|----------|---------|
| spring.datasource.url | JDBC URL for PostgreSQL. |
| ddl-auto=update | Auto-updates tables when entity changes. |

## 5. Person Entity Explained 👤

### `Person.java`
```java
package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;

@Entity  // Maps this class to a database table
@Data    // Auto-generates getters, setters, toString()
@Table(name = "table_1")  // Custom table name
public class Person {
    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;
    
    private String name;
    private String email;
}
```

### Key Annotations

| Annotation | Purpose |
|------------|---------|
| @Entity | Marks class as a JPA entity. |
| @Id + @GeneratedValue | Auto-generates unique IDs. |
| @Data (Lombok) | Avoids manual getters/setters. |

## 6. PersonRepo Repository 📂

### `PersonRepo.java`
```java
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
    // Inherits CRUD methods: save(), findAll(), deleteById(), etc.
}
```

### Why Use It?
No SQL needed! Spring Data JPA provides:

```java
personRepo.save(person);      // Insert
personRepo.findAll();         // Select all
personRepo.findById(1L);      // Select by ID
personRepo.deleteById(1L);    // Delete
```

## 7. How It Works Together 🔄
- Spring Boot → Reads `application.properties` → Connects to PostgreSQL.
- Hibernate → Creates/updates `table_1` from `Person.java`.
- `PersonRepo` → Handles database operations.

## 8. Testing & Verification ✅
Run the app → Check logs for:

```
Hibernate: create table table_1 (id bigserial not null, email varchar(255), name varchar(255), primary key (id))
```

Verify in PGAdmin4:
- Table `table_1` exists in `rifat_db`.
- Columns: `id`, `name`, `email`.

## 9. Troubleshooting 🚨

| Issue | Fix |
|-------|-----|
| Connection refused | Ensure PostgreSQL is running (`sudo service postgresql start`). |
| Authentication failed | Double-check username/password in `application.properties`. |
| Lombok errors | Install the Lombok plugin for your IDE. |

## 10. Next Steps 🔜
- Create a `PersonController` to expose REST APIs.
- Add validation (e.g., `@NotBlank` for required fields).
- Write unit tests with `@DataJpaTest`.

## Final Notes 📝
✅ This setup avoids manual SQL for basic CRUD.  
✅ Annotations like `@Entity`, `@Data` reduce boilerplate code.  
✅ Easy to scale and test.

**Questions? Open an issue! 😊**

**Happy Coding! 💻🎉**
