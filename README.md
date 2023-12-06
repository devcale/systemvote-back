# User Management REST API

This Spring Boot application provides a RESTful API for managing user data, allowing CRUD operations on a user database. Users can register, log in, and perform basic operations on their accounts.

## Features

- **User Registration**: New users can sign up by providing necessary details such as username, email, and password.
- **User Authentication**: In order to access the API, users need to be authenticated. Users can get their authentication token by using the login endpoint ```/auth/login``` and providing their login details.

- **CRUD Operations on Users**:
  - **Create**: Creates a new user.
  - **Read**: Retrieve the list of users.
  - **Update**: Modify user information.
  - **Delete**: Remove a user account.

## Technologies Used

- **Spring Boot**: Framework used for creating the server-side logic.
- **Spring Security**: Provides authentication and authorization functionality.
- **Spring Data JPA**: Simplifies database interactions and CRUD operations.
- **MySQL**: Database used to store user information.

## Getting Started


### Installation

1. Clone this repository.

```git clone https://github.com/your-username/user-management-api.git```

2. Navigate to the project directory.


```cd systemvote-back```

3. Configure the database settings in application.properties and secrets.properties.

```
# application.properties
spring.config.import=optional:secrets.properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
security.jwt.secret-key = <DEFINE YOUR OWN JWT KEY>
```

```
# secrets.properties
spring.datasource.url=jdbc:mysql://<YOUR DB LOCATION>
spring.datasource.username=<YOUR DB USERNAME>
spring.datasource.password=<YOUR DB PASSWORD>
```
4. Run the application using your IDE of choice or by using Maven:
```
mvn spring-boot:run
```
## Usage

Once the application is running, you can interact with the API using HTTP requests:

### User Registration
> Functionality still in development <br>

**Registration Route**

    POST /auth/register

**Registration Body**

```
{
	"username": "john_doe",
	"email": "john_doe@example.com",
	"password": "password123f"
}
```

### User Login 
**Login Route**

    POST /auth/login

**Login Body**

```
{
	"username": "john_doe",
	"password": "password123f"
}
```

### CRUD Operations
> CRUD Operations are only available to Users who have already signed in, and provide the authentication bearer token.

    GET /api/user
        Retrieve the list of registered users.

    GET /api/user/{id}
        Retrieve details of a specific user by ID.

    PUT /api/user/{id}
        Update user information by providing updated details in the request body.

    DELETE /api/user/{id}
        Delete a user account by ID.