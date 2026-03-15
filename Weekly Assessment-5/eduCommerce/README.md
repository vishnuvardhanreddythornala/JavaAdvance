# EduCommerce - Student Performance & Attendance Management System

A cloud-native microservices application built with Spring Boot and Spring Cloud.

## Architecture

```
Client (Postman / UI)
        |
   API Gateway (JWT Auth - Port 8080)
        |
   _____|_______________________
   |           |               |
Student     Attendance      Result
Service     Service         Service
(8081)      (8082)          (8083)
   |           |               |
   ----  Eureka (8761)  --------
              |
        Config Server (8888)
              |
         Git / Native Config
              |
         Zipkin (9411)
```

## Services

| Service           | Port | Description                              |
|-------------------|------|------------------------------------------|
| Config Server     | 8888 | Centralized configuration management     |
| Eureka Server     | 8761 | Service discovery                        |
| API Gateway       | 8080 | JWT authentication + routing             |
| Student Service   | 8081 | Registration, login, courses, enrollment |
| Attendance Service| 8082 | Attendance tracking                      |
| Result Service    | 8083 | Exam results and GPA                     |
| Zipkin            | 9411 | Distributed tracing UI                   |

## Quick Start

### Option 1: Run with Docker Compose

```bash
docker-compose up --build
```

### Option 2: Run Manually

Start services in this order:

```bash
# 1. Config Server
cd config-server && mvn spring-boot:run

# 2. Eureka Server
cd eureka-server && mvn spring-boot:run

# 3. API Gateway
cd api-gateway && mvn spring-boot:run

# 4. Domain Services (in any order)
cd student-service && mvn spring-boot:run
cd attendance-service && mvn spring-boot:run
cd result-service && mvn spring-boot:run
```

## API Usage

### Authentication

**Register a student:**
```http
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "department": "Computer Science",
  "semester": 3
}
```

**Login:**
```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

Use the returned `token` in the `Authorization: Bearer <token>` header for all subsequent requests.

### Student APIs
```
GET    /students              - List all students
GET    /students/{id}         - Get student by ID
PUT    /students/{id}         - Update student
DELETE /students/{id}         - Delete student
```

### Course APIs
```
POST   /courses               - Create course
GET    /courses               - List all courses
PUT    /courses/{id}          - Update course
DELETE /courses/{id}          - Delete course
```

### Enrollment APIs
```
POST   /enroll                        - Enroll student {studentId, courseId}
GET    /students/{id}/courses         - Get student courses
```

### Attendance APIs
```
POST   /attendance                          - Mark attendance
GET    /attendance/student/{studentId}      - Student attendance
GET    /attendance/course/{courseId}        - Course attendance
GET    /attendance/summary?studentId=&courseId= - Attendance summary
PUT    /attendance/{id}                     - Update record
DELETE /attendance/{id}                     - Delete record
```

### Result APIs
```
POST   /results                            - Add result
GET    /results/student/{studentId}        - Student results
GET    /results/course/{courseId}          - Course results
GET    /results/student/{studentId}/gpa    - Calculate GPA
GET    /results/student/{studentId}/report - Full student report
PUT    /results/{id}                       - Update result
DELETE /results/{id}                       - Delete result
```

## Environment Variables

| Variable       | Default                                            | Description           |
|----------------|----------------------------------------------------|-----------------------|
| JWT_SECRET     | educommerce-super-secret-key-for-jwt-signing-2024  | JWT signing key       |
| DB_PASSWORD    | password                                           | Database password     |
| ZIPKIN_URL     | http://localhost:9411                              | Zipkin server URL     |
| EUREKA_URL     | http://localhost:8761/eureka/                      | Eureka server URL     |
| CONFIG_GIT_URI | (local native profile used by default)             | Git config repo URI   |

## Spring Cloud Features Implemented

- **Config Server** - Centralized config with native profile (switch to git for production)
- **Eureka Discovery** - All services auto-register on startup
- **API Gateway** - JWT filter + Resilience4j circuit breakers on all routes
- **OpenFeign** - Inter-service calls (Attendance calls Student; Result calls both)
- **Resilience4j** - Circuit breakers with fallback methods on all Feign clients
- **Distributed Tracing** - Micrometer + Zipkin (view at http://localhost:9411)
- **Secrets Management** - Sensitive values via environment variables

## H2 Console (Development)

Each service exposes an in-memory H2 database console:
- Student: http://localhost:8081/h2-console  (JDBC: jdbc:h2:mem:studentdb)
- Attendance: http://localhost:8082/h2-console
- Result: http://localhost:8083/h2-console
