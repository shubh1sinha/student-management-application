# student-management-application
CRUD Operation using Java 21

Tools and Stack - Java 21, maven, Spring, Itellij, Postman, PostgreSql, lombok

DB Schema
The columns are:
id: A serial column that auto-increments and serves as the primary key.
name: A string (up to 255 characters) that holds the name of the student.
class: An integer representing the grade/class of the student.
contact: A 10-digit string representing the student's contact number.
age: An integer representing the student's age.
usid: Unique Student Id
UNIQUE (name, age, RANDOM NUMBER): A constraint to ensure that no two rows have the same combination of name, age, and contact.

Only BE API(S)
EndPonts
     Query: 
           (Get) fetchAllStudent: [localhost:8080]/query/students
           (Get) fetchStudentByName:[localhost:8080]/query/student?name=[example]
     Mutation:
            (post)  saveStudent: [localhost:8080]/api/student (RequestBody) {data:{name:"", age:[>5}, contact[10digit], studentClass:[int]}}
            (put)   updateStudent: [localhost:8080]/api/student (RequestBody) {data:student:{name:"", age:[>5}, contact[10digit], studentClass:[int], usid:[""]}, fieldsToBeUpdated:[setOfStrings]}
            (delete)deleteStudent: [localhost:8080]/api/student (RequestBody) {data:{name:"", age:[>5}, contact[10digit], studentClass:[int]}}

