# Patient Management

A learning project for **Spring Boot**. I'm building a patient management system as a set of **microservices**. Each service runs in a **Docker** container, and the plan is to deploy everything to **AWS**.

The goal isn't to ship a product. It's to learn how a real backend is put together, from a single REST service to a containerised, cloud-deployed system.

## What I'm learning

- **Spring Boot**: REST controllers, services, repositories, dependency injection, validation
- **Spring Data JPA / Hibernate**: entities, repositories, mapping database rows to Java objects
- **DTOs and mappers**: keeping the API's request and response shapes separate from the database entities
- **Microservices**: splitting the system into small, independent services that talk to each other
- **Docker**: packaging each service and its database into containers
- **AWS**: deploying and running the services in the cloud

## Services

| Service | Port | Status | Description |
|---|---|---|---|
| `patient-service` | 4000 | In progress | CRUD API for patient records |

More services will be added as the project grows.

## Tech stack

- Java 17
- Spring Boot 4 (Web MVC, Data JPA, Validation)
- H2 in-memory database for local development; PostgreSQL later
- Maven
- Docker *(planned)*
- AWS *(planned)*

## Project structure

```
patient-management/
└── patient-service/
    └── src/main/java/com/darawsheh/pm/patientservice/
        ├── controller/   # REST endpoints
        ├── service/      # business logic
        ├── repository/   # Spring Data JPA repositories
        ├── model/        # JPA entities (e.g. Patient)
        ├── DTO/          # request/response objects sent over the API
        └── mapper/       # converts entities <-> DTOs
```

## Running locally

```bash
cd patient-service
./mvnw spring-boot:run     # or: mvn spring-boot:run
```

The service starts on `http://localhost:4000`.

### Endpoints

| Method | Path | Description |
|---|---|---|
| `GET` | `/patients` | List all patients |
| `POST` | `/patients` | Create a patient |

Example:

```bash
curl http://localhost:4000/patients
```

### H2 console

While the app is running, open `http://localhost:4000/h2-console` to browse the in-memory database:

- JDBC URL: `jdbc:h2:mem:test`
- Username: `admin`
- Password: `admin`

## Roadmap

- [x] Patient service: list and create patients
- [ ] Patient service: update and delete
- [ ] Swap H2 for PostgreSQL
- [ ] Dockerfile for each service and a `docker-compose` setup
- [ ] Additional microservices and communication between services
- [ ] API gateway
- [ ] Deploy to AWS
