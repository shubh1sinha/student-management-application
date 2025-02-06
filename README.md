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
│   │   │   │   └── assignment/
│   │   │   │       └── management/
│   │   │   │           ├── controller/       # REST API Controllers
│   │   │   │           ├── model/            # Entity classes (e.g., Student)
│   │   │   │           ├── repository/       # Repository interfaces (CRUD operations)
│   │   │   │           └── service/			# Service layer (business logic)
│   │   │   │           └── utility/          #utility layer
│   │   │   │           └── exception/        #global exception handler
│   │   └── resources/               # Configuration files (application.properties, etc.)
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

Mutation Api(s) Input Mapping
-Generic Structure: Using a generic class like APIRequest<T> means that the request class can be reused for multiple types of data (StudentDTO, TeacherDTO, etc.), making the code more modular and maintainable.
-Flexibility: The APIRequest<T> can easily be extended for other types of requests beyond just SaveStudentRequest, making it easy to manage different entities within the system.
-Clean API Requests: By encapsulating the request logic in classes like APIRequest, the API layer remains clean, separating the concerns of request structure and actual business logic.
-Jackson Annotations: The annotations help with handling JSON serialization/deserialization, making it easier to convert between Java objects and JSON, and also handle situations where the request JSON may contain unknown or null values.
