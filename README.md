# Secure User Management System

## Overview
Secure User Management System is a Java Web Application developed using JSP, JDBC, DAO, Service Layer, and MySQL. It provides basic user management operations with a clean layered architecture.

## Features

- User Registration
- User Login
- Forgot Password
- Update Password
- Delete User
- View All Users

## Technologies Used

- Java
- JSP
- JDBC
- Maven
- MySQL
- Apache Tomcat 10
- HTML
- CSS
- IntelliJ IDEA

## Project Structure

```
src/
 ├── main/
 │   ├── java/
 │   │   └── com.codegnan.app.javawebapp10
 │   │       ├── dao
 │   │       ├── dto
 │   │       ├── entity
 │   │       └── service
 │   └── webapp/
 │       ├── WEB-INF
 │       ├── *.html
 │       ├── *.jsp
 │       └── *.css
```

## Database

Database Name:

```
sql_db
```

Tables:

- users
- credentials

## How to Run

1. Clone the repository.
2. Open in IntelliJ IDEA.
3. Configure MySQL database.
4. Update `DatabaseUtility.java` with your database credentials.
5. Build the project using Maven.
6. Deploy on Apache Tomcat 10.
7. Open:

```
http://localhost:8080/java-webapp-10
```

## Author

Anu A
