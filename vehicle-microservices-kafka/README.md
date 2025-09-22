Vehicle Microservices with Kafka - demo
Services included:
- eureka-server (8761)
- api-gateway (8080)
- auth-service (8081) -> JWT auth, refresh tokens
- vehicle-service (8082) -> REST + publishes VehicleCreated events to Kafka
- audit-service (8083)  -> consumes vehicle.events and persists audit logs to MySQL
- billing-service (8084) -> consumes vehicle.events and simulates billing actions
- mysql (3306)
- zookeeper (2181)
- kafka (9092)

Run with Docker Compose:
  docker-compose up --build
