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

## 1. Create a New Realm

1. Log in to the Keycloak Admin Console (e.g., `http://localhost:8081/auth/admin`).
2. In the left-hand menu, click on `Realms`.
3. Click on the `Add realm` button.
4. Enter a name for your new realm (e.g., `my-realm`) and click `Create`.

## 2. Create a New Client

1. In the left-hand menu, select your newly created realm (`my-realm`).
2. Navigate to `Clients` in the menu.
3. Click on the `Create` button.
4. Fill in the following details for the client:
   - **Client ID**: Choose a name for your client (e.g., `my-client`).
   - **Client Protocol**: Select `openid-connect`.
   - **Root URL**: Provide the base URL for your application (e.g., `http://localhost:8081`).
5. Click `Save`.

## 3. Configure Web Origins Under the Client

1. After creating the client, click on the client name (`my-client`).
2. Go to the `Settings` tab.
3. In the **Web Origins** field, enter the URL of your web application. For local development, you can use:
