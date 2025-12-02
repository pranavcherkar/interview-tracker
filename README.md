
# Interview Tracker — Backend

A Spring Boot backend application to track interview progress for job applications. Supports managing connections, interview rounds, enforcing strict business rules, audit timestamps, and deployment with Docker on Render using PostgreSQL.

---

## Features

| Category               | Description                                                                        |
| ---------------------- | ---------------------------------------------------------------------------------- |
| **Connections**        | Store person, company, role, connection status, message status, and notes          |
| **Interview Rounds**   | Add multiple rounds with interviewer name, mode, date, duration, result, and notes |
| **Rules & Validation** | Prevent duplicate connections and invalid status transitions                       |
| **Audit Tracking**     | Each record stores created and updated timestamps                                  |
| **Cascade Delete**     | Deleting a connection removes associated interview rounds                          |
| **Robust API**         | Full CRUD for connections and interviews                                           |
| **Deployment Ready**   | Dockerfile + PostgreSQL + Render setup                                             |

---

## Tech Stack

* **Java 17**
* **Spring Boot 4**
* **Spring Data JPA + Hibernate**
* **PostgreSQL (production) / H2 (development)**
* **Docker**
* **Lombok (optional)**
* **Maven**

---

## Database Entities

### 1. `Connection`

| Field            | Description                                                                       |
| ---------------- | --------------------------------------------------------------------------------- |
| id               | Primary key                                                                       |
| personName       | Candidate or contact person                                                       |
| company          | Company name                                                                      |
| role             | Applied role                                                                      |
| connectionStatus | SENT / MESSAGED / ACCEPTED / INTERVIEW_SCHEDULED / INTERVIEWED / OFFER / REJECTED |
| messageStatus    | NOT_SENT / SENT / SEEN / REPLIED                                                  |
| replyNote        | Optional notes                                                                    |
| createdAt        | Record creation date                                                              |
| updatedAt        | Last update timestamp                                                             |
| interviews       | List of interview rounds                                                          |

### 2. `Interview`

| Field           | Description              |
| --------------- | ------------------------ |
| id              | Primary key              |
| connectionId    | FK to connection         |
| roundNumber     | Round (1,2,3...)         |
| interviewerName | Interviewer              |
| mode            | ONLINE / OFFLINE / VIDEO |
| duration        | Time duration            |
| date            | Interview date           |
| result          | PENDING / PASS / FAIL    |
| notes           | Text notes               |
| createdAt       | Created timestamp        |
| updatedAt       | Updated timestamp        |

---

## Business Rules

| Rule                                                  | Behavior                                   |
| ----------------------------------------------------- | ------------------------------------------ |
| No duplicate connections                              | Same person + company + role blocked       |
| Status cannot move backward                           | Example: Cannot go from INTERVIEWED → SENT |
| Cannot edit company/person/role once interviews exist | Locked after first round added             |
| Cannot add later round if earlier round failed        | e.g. Round 3 cannot exist if Round 1 FAIL  |
| Only last round can be deleted                        | Prevents number gaps                       |
| Interview creation must have unique round numbers     | No duplicates                              |

---

## REST API Endpoints

### Connections

| Method | Endpoint                | Description                    |
| ------ | ----------------------- | ------------------------------ |
| GET    | `/api/connections`      | Get all connections            |
| GET    | `/api/connections/{id}` | Get connection by ID           |
| POST   | `/api/connections`      | Create new connection          |
| PUT    | `/api/connections/{id}` | Update connection              |
| DELETE | `/api/connections/{id}` | Delete connection + interviews |

### Interviews

| Method | Endpoint                         | Description                   |
| ------ | -------------------------------- | ----------------------------- |
| GET    | `/api/interviews?connectionId=x` | Get all rounds for connection |
| POST   | `/api/interviews`                | Create round                  |
| PUT    | `/api/interviews/{id}`           | Update round                  |
| DELETE | `/api/interviews/{id}`           | Delete last round only        |

---

## Local Setup

### 1. Clone repository

```bash
git clone https://github.com/pranavcherkar/interview-tracker.git
cd interview-tracker
```

### 2. Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

### 3. H2 Console (local only)

```
http://localhost:8080/h2-console
```

---

## Deployment (Render + Docker)

### Dockerfile (multi-stage build)

```dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY Interview_tracker/interviewtracker/pom.xml .
COPY Interview_tracker/interviewtracker/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

### Environment Variables (Render)

| Key         | Example                                |
| ----------- | -------------------------------------- |
| DB_URL      | `jdbc:postgresql://host:5432/database` |
| DB_USERNAME | postgres                               |
| DB_PASSWORD | ******                                 |

---

## Production Application Properties

```properties
spring.application.name=interviewtracker
server.port=${PORT:8080}

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driverClassName=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true

spring.h2.console.enabled=false
```

---

## Future Enhancements

* Authentication (JWT)
* Pagination & sorting
* Scheduled reminders and email notifications
* PDF and XLSX export improvements
* Add test coverage & CI pipeline

---

## License

MIT License — free to use and modify.

---
