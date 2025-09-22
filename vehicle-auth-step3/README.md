# Vehicle Authentication Module (Spring Boot)

This is a minimal Spring Boot skeleton implementing JWT authentication for the Vehicle Insurance Management System.
It uses H2 in-memory database for ease of testing.

APIs included:
- POST /auth/register  -> register user (roles: USER by default)
- POST /auth/login     -> authenticate and receive JWT
- POST /auth/refresh   -> refresh token (simple example)
- GET  /vehicles/view  -> accessible by ROLE_USER or ROLE_ADMIN
- POST /vehicles/add   -> accessible by ROLE_ADMIN only

How to run:
1. Build: mvn clean package
2. Run: java -jar target/vehicle-auth-0.0.1-SNAPSHOT.jar
3. H2 console: http://localhost:8080/h2-console (jdbc:h2:mem:testdb, user sa, no password)

Note: Replace JWT secret in application.properties for production.
