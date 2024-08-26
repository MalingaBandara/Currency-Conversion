# Currency Conversion Microservice

This repository contains the source code for the Currency Conversion Microservice of the Currency Conversion and Exchange API project. 

## Project Overview

This repository contains the Currency Conversion Microservice, a core component of the Currency Conversion and Exchange API project. This microservice is responsible for converting currencies based on predefined exchange rates and is designed to work seamlessly within a distributed microservices architecture.

## Key Features

- Currency conversion functionality using predefined rates.
- Service discovery integration with Eureka.
- Dockerized for easy deployment and scalability.
- REST API endpoints for currency conversion operations.

## Technologies Used

- **Java**
- **Spring Boot**
- **H2 Database**
- **Eureka Server**
- **Docker**

## Project Structure and Code Explanation

1. **Conversion Logic**:
   - Implements currency conversion based on stored exchange rates, accessible through RESTful endpoints.
   ```java
   @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
   public CurrencyConversion calculateConversion(
       @PathVariable String from,
       @PathVariable String to,
       @PathVariable BigDecimal quantity) {
       // Logic to perform conversion
   }
   ```

2. **Service Discovery**:
   - Integrates with Eureka for dynamic service registration and discovery.
   ```yaml
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8761/eureka/
   ```

3. **Database Configuration**:
   - The microservice uses H2 as an in-memory database for storing and retrieving conversion rates.

4. **Docker Integration**:
   - The microservice is fully containerized, allowing for easy deployment using Docker Compose.

## Commits and Project Evolution

1. **Initial Setup**: Created the project structure and basic conversion logic.
2. **Service Discovery Integration**: Added Eureka client configuration for service registration.
3. **Containerization**: Added Docker support for seamless deployment.

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/MalingaBandara/Currency-Conversion.git
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the service:
   ```bash
   mvn spring-boot:run
   ```
4. Alternatively, use Docker:
   ```bash
   docker-compose up
   ```

## Purpose and Future Enhancements

This microservice demonstrates currency conversion functionality within a scalable, distributed architecture. Future enhancements may include real-time exchange rate updates, caching, and circuit breakers for fault tolerance.

## Main Repository

- [Currency Conversion and Exchange API](https://github.com/MalingaBandara/Currency-Conversion-Exchange-Microservices)

## What's Covered in This Microservice

- Implementation of currency conversion logic.
- Integration with the Naming Server Microservice for service discovery.
- Utilization of Docker for containerization and deployment.


## License

This project is licensed under the MIT License.
