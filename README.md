# keycloak-rest-api-backend

Project integrates with Keycloak for authentication and handles RESTful APIs. It uses JWT tokens for secure authentication and authorization through Keycloak. The application is designed to manage API requests and respond with the appropriate data while ensuring that only authenticated users can access protected resources.

## Features

- Built with Spring Boot and Maven
- PostgreSQL database integration
- JWT authentication with Keycloak

## Prerequisites

Before running the application, following have to be installed:

- Java 8 or later
- Maven
- IntelliJ IDEA (or any IDE of your choice)
- PostgreSQL database
- Keycloak for JWT authentication (on Docker for example)

## Keycloak setup

1. Create a New Realm

2. Create a New Client

3. Configure Web Origins Under the Client

4. Create roles (role client_admin has been setup to access all other roles data as well)

5. Create realm roles and assign client roles previously created

6. Create users, assign roles to users and test

