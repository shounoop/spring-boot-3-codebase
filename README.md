
# Spring Boot 3 Codebase

This repository contains a Spring Boot 3 codebase currently in development.

## Running the Application

### 1. Using Maven
To run the application with Maven, execute the following command:

```shell
mvn spring-boot:run
```

### 2. Using Java
To run the application using Java, first package the application with Maven and then run the generated JAR file:

```shell
mvn clean package
java -jar target/spring-boot-3-0.0.1-SNAPSHOT.jar
```

### 3. Using Docker Compose
To run the application using Docker Compose, use the following command:

```shell
docker-compose up -d
```

## Project Setup

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- Docker (for Docker Compose setup)

### Dependencies
- **Spring Boot 3** (Parent Dependency)
- **Spring Boot Starter Data JPA** (for data access)
- **Spring Boot Starter Web** (for building web applications)
- **Spring Boot Starter Test** (for testing)
- **MySQL Connector** (for connecting to MySQL database)
- **MapStruct** (for mapping between DTOs and entities)
- **Lombok** (for reducing boilerplate code)
- **Lombok MapStruct Binding** (for Lombok and MapStruct integration)
- **Jakarta Validation API** (for bean validation)
- **Hibernate Validator** (for validation implementation)
- **SpringDoc OpenAPI Starter WebMVC UI** (for Swagger API documentation)
- **SonarQube Maven Plugin** (for code quality analysis)
- **Spring Security** (for authentication and authorization)
- **Spring Web** (for building RESTful web services)
- **Spring Boot DevTools** (for enhanced development experience)
- **Spring Boot Actuator** (for monitoring and management)

## References

- [How to Implement Internationalization (i18n) in Spring Boot](https://medium.com/@AlexanderObregon/how-to-implement-internationalization-i18n-in-spring-boot-aea2c62c1bfa)
- [Using Swagger 3 with Spring Boot 3](https://www.bezkoder.com/spring-boot-swagger-3/?__cf_chl_tk=H7lF7qCwws2LOul_nVp36MPgmCCdPMjo4HtsvwERhpE-1734493690-1.0.1.1-c2FoQOS9_7UG6qU4sDjL775zZFSYzCzFA.dS4XKR2g4)
- Integrating SonarQube with Spring Boot:
  - [SonarQube Introduction and Configuration with Spring Boot](https://medium.com/@salvipriya97/sonarqube-introduction-and-configuration-with-spring-boot-project-6fb92f4fe268)
  - [Integration of SonarQube with Spring Boot](https://www.geeksforgeeks.org/integration-of-sonarqube-with-springboot/)
- [How to import data from Excel file in Spring Boot](https://springjava.com/spring-boot/how-to-import-data-from-excel-file-in-spring-boot)
- [Export Data to Excel file in Spring Boot](https://springjava.com/spring-boot/export-data-to-excel-file-in-spring-boot)
- [Logging With AOP in Spring](https://www.baeldung.com/spring-aspect-oriented-programming-logging)
- [Using MapStruct With Lombok](https://www.baeldung.com/java-mapstruct-lombok)

## Todo
- Add base entity, base controller, base service, common mapper
- Handle exporting Excel file concurrently using CompletableFuture (CompletableFuture in Spring Boot is part of Java's java.util.concurrent package and provides a powerful way to handle asynchronous programming. It allows running tasks in parallel, combining multiple asynchronous operations, and handling their results when they complete)
- Custom Executor (ThreadPoolTaskExecutor) with Spring Boot Async
- Using Keycloak with Spring Boot
- Socket.IO, MQTT
- MinIO
- Caching (Redis, Memcached)
- Message queue (Kafka, RabbitMQ, ActiveMQ)
- Unit testing
- Docx4java
- Monitoring & Logging using Prometheus, ELK Stack, Grafana
