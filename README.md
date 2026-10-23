# Employee Management REST API

## Project Overview

The Employee Management REST API is a Spring Boot application developed to manage employee records. It allows users to create, retrieve, update, delete, search, and filter employee information stored in a MySQL database.

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Jakarta Validation
- Swagger UI (Springdoc OpenAPI)
- Spring Tool Suite (STS)

## Features

- Create a single employee or multiple employees.
- Retrieve all employees or retrieve an employee by ID.
- Update employee information.
- Delete employee records.
- Search employees by name, email, or department.
- Filter employees by ACTIVE or INACTIVE status.
- Paginate and sort employee results.
- Validate employee input.
- Handle exceptions with appropriate HTTP status codes.
- Explore and test API endpoints using Swagger UI.

## Project Structure

```text
employee-management/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/employee/employeemanagement/
│       │       ├── EmployeeManagementApplication.java
│       │       ├── controller/
│       │       │   └── EmployeeController.java
│       │       ├── service/
│       │       │   └── EmployeeService.java
│       │       ├── repository/
│       │       │   └── EmployeeRepository.java
│       │       ├── entity/
│       │       │   └── Employee.java
│       │       ├── dto/
│       │       │   └── EmployeeDTO.java
│       │       └── exception/
│       │           └── GlobalExceptionHandler.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

## Requirements

- Java 17-compatible project configuration
- Maven
- MySQL Server
- Spring Tool Suite (STS)

## Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE employee_db;
```

Configure `src/main/resources/application.properties`:

```properties
spring.application.name=employee-management
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
server.port=8080
```

**Note:** These credentials are for local development. Before uploading the project to a public GitHub repository, keep your database password private and use an environment variable or another secure configuration method.

## How to Run the Application

1. Open Spring Tool Suite (STS).
2. Import the project as an existing Maven project.
3. Make sure MySQL Server is running.
4. Create the `employee_db` database.
5. Configure the database credentials in `application.properties`.
6. Allow Maven to download the required dependencies.
7. Run `EmployeeManagementApplication.java` as a Java application.
8. The application will run at `http://localhost:8080`.

## API Endpoints

Base URL: `http://localhost:8080/api/employees`

1. **Create an Employee**
   - Method: `POST`
   - Endpoint: `/api/employees`
   - Description: Creates a new employee record.

2. **Create Multiple Employees**
   - Method: `POST`
   - Endpoint: `/api/employees/bulk`
   - Description: Creates multiple employee records in a single request.

3. **Retrieve Employees**
   - Method: `GET`
   - Endpoint: `/api/employees`
   - Description: Retrieves employees with optional search, status filtering, pagination, and sorting.

4. **Retrieve an Employee by ID**
   - Method: `GET`
   - Endpoint: `/api/employees/{id}`
   - Description: Retrieves the employee with the specified ID.

5. **Update an Employee**
   - Method: `PUT`
   - Endpoint: `/api/employees/{id}`
   - Description: Updates the details of an existing employee.

6. **Delete an Employee**
   - Method: `DELETE`
   - Endpoint: `/api/employees/{id}`
   - Description: Deletes the employee with the specified ID.

## Sample POST Request

**Method:** `POST`

**Endpoint:** `/api/employees`

Request body:

```json
{
  "name": "Shahada",
  "email": "shahadavt@gmail.com",
  "department": "IT",
  "status": "ACTIVE"
}
```

A successful request returns HTTP `201 Created` and the created employee record, including its generated ID.

## Search, Filtering, Pagination, and Sorting

Example request:

```text
/api/employees?search=Shahada&status=ACTIVE&page=0&size=10&sortBy=name&direction=asc
```

- `search`: Searches employee names, emails, and departments.
- `status`: Filters employees by ACTIVE or INACTIVE status.
- `page`: Specifies the page number, starting from `0`.
- `size`: Specifies the number of records per page. The default is `10`, and the maximum is `100`.
- `sortBy`: Specifies the field used for sorting, such as `name` or `id`.
- `direction`: Specifies the sorting direction: `asc` or `desc`.

## Validation and Exception Handling

The application validates employee data and handles errors using appropriate HTTP status codes.

- `400 Bad Request`: Invalid input, status, pagination, or sorting parameters.
- `404 Not Found`: The requested employee ID does not exist.
- `201 Created`: Employee created successfully.
- `204 No Content`: Employee deleted successfully.

## Swagger UI

Start the application and open the following URL in your browser:

`http://localhost:8080/swagger-ui/index.html`

Swagger UI allows you to view, explore, and test the available API endpoints.

## Author

Shahada
