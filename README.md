# Spring Boot 3 Template

A modern, extensible Spring Boot 3 project template with advanced features for scalable enterprise applications.

---

## Table of Contents
- [Spring Boot 3 Template](#spring-boot-3-template)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Implemented Features](#implemented-features)
  - [Project Setup](#project-setup)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Running the Application](#running-the-application)
    - [Using Maven](#using-maven)
    - [Using Java](#using-java)
    - [Using Docker Compose](#using-docker-compose)
  - [References](#references)
  - [Todo](#todo)
  - [Contributing](#contributing)

---

## Overview
This repository provides a robust starting point for Spring Boot 3 projects, including multi-database support, advanced transaction management, real-time capabilities, and best practices for modern Java development.

## Implemented Features
- Multi-database configuration and multiple transaction managers (support for connecting and managing more than one database, handling transactions across multiple databases)
- Real-time integration with Firebase
- Base entity, base controller, base service, and common mapper for code reusability
- Spring Data JPA for data access
- Spring Web for building RESTful APIs
- Spring Security for authentication and authorization
- MapStruct and Lombok integration for DTO/entity mapping and reduced boilerplate
- Jakarta Validation API and Hibernate Validator for bean validation
- Swagger/OpenAPI documentation (SpringDoc)
- SonarQube Maven Plugin for code quality analysis
- Docker & Docker Compose support for containerization
- Spring Boot DevTools for enhanced development experience
- Spring Boot Actuator for monitoring and management
- Internationalization (i18n) support
- Excel import/export functionality
- Logging with AOP

## Project Setup
### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- Docker (for Docker Compose)

### Installation
1. Clone the repository:
   ```shell
   git clone https://github.com/shounoop/springboot3-template.git
   cd codebase-spring-boot-3
   ```
2. Configure your database(s) and environment variables as needed in `src/main/resources/application.yml`.

## Running the Application
### Using Maven
```shell
mvn spring-boot:run
```

### Using Java
```shell
mvn clean package
java -jar target/spring-boot-3-0.0.1-SNAPSHOT.jar
```

### Using Docker Compose
```shell
docker-compose up -d
```

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
- Implement base entity, base controller, base service, and common mapper for code reusability.
- Enable concurrent Excel file export using CompletableFuture for efficient asynchronous processing.
- Integrate a custom executor (ThreadPoolTaskExecutor) with Spring Boot's @Async for advanced async task management.
- Add authentication and authorization using Keycloak.
- Integrate real-time communication with Socket.IO and MQTT.
- Add MinIO for object storage solutions.
- Implement caching with Redis and Memcached.
- Integrate message queues: Kafka, RabbitMQ, and ActiveMQ.
- Expand unit testing coverage.
- Add support for DOCX file manipulation using Docx4j.
- Set up monitoring and logging with Prometheus, ELK Stack, and Grafana.
- Enable and configure CORS for cross-origin requests.
- Export word with template using docx4j
- Swagger: Strategies for microservices

## Contributing
Contributions are welcome! Please open issues or submit pull requests for improvements, bug fixes, or new features.

---
