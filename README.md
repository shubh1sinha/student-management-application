Based on the repository, here’s a basic project structure and architecture:

### Project Structure:
```
student-management-application/
│
├── db-scripts/                    # Contains database scripts for setting up and managing the DB
│
├── src/                           # Java source files
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── example/
│   │   │   │       └── student/
│   │   │   │           ├── controller/       # REST API Controllers
│   │   │   │           ├── model/            # Entity classes (e.g., Student)
│   │   │   │           ├── repository/       # Repository interfaces (CRUD operations)
│   │   │   │           └── service/          # Service layer (business logic)
│   │   └── resources/               # Configuration files (application.properties, etc.)
│   ├── test/                       # Unit and integration tests
│   │   └── java/
│   │       ├── com/
│   │       │   └── example/
│   │       │       └── student/
│   │       │           ├── controller/       # Test cases for controllers
│   │       │           └── service/          # Test cases for services
│
├── .gitignore                      # Specifies files and directories to be ignored by Git
├── .gitattributes                  # Git attributes file
├── README.md                       # Project documentation
├── mvnw                            # Maven wrapper
├── mvnw.cmd                        # Maven wrapper for Windows
├── pom.xml                         # Maven project file
```

### Architecture:
1. **Model**: 
   - Defines the `Student` entity, including properties like `id`, `name`, `class`, `contact`, `age`, and `usid` (Unique Student ID).

2. **Controller**: 
   - Exposes REST APIs for CRUD operations: 
     - `GET /query/students` to fetch all students.
     - `GET /query/student?name=<name>` to fetch a student by name.
     - `POST /api/student` to create a new student.
     - `PUT /api/student` to update an existing student.
     - `DELETE /api/student` to delete a student.

3. **Service**:
   - Implements business logic related to student management, such as validating input and managing database interactions.

4. **Repository**:
   - Provides the data access layer using Spring Data JPA for performing CRUD operations on the database.

5. **Database**:
   - The application uses PostgreSQL for storing student data.

The stack used includes:
- **Java 21** for application logic.
- **Spring Boot** for building REST APIs.
- **PostgreSQL** for database.
- **Maven** for dependency management.
- **Lombok** for reducing boilerplate code.