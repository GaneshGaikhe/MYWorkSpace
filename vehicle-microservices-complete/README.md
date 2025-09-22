Vehicle Microservices Complete - demo
Services included:
- eureka-server (8761)
- api-gateway (8080)
- auth-service (8081) -> full JWT auth (access + refresh + logout), role promotion, tests
- vehicle-service (8082) -> validates JWT and enforces ROLE_ADMIN on add
- mysql (3306) via docker-compose

Run with Docker Compose:
  docker-compose up --build
