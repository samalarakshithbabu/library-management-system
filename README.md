# Library Management System

A Java-based Library Management System demonstrating Object-Oriented Programming concepts with MySQL database integration.

## Features

- **Book Management**: Add, view, search books
- **Student Management**: Add student records
- **Issue/Return System**: Issue books to students and handle returns
- **Search Functionality**: Search books by title or author (demonstrates polymorphism)

## OOP Concepts Implemented

1. **Encapsulation**: Private fields with getters/setters in all classes
2. **Inheritance**: 
   - `Student` extends `Person`
   - `Librarian` extends `User`
3. **Polymorphism**: Method overloading in `LibraryDAO.searchBooks()`
4. **Abstraction**: Abstract `User` class with abstract `login()` method

## Prerequisites

- Java JDK 8 or higher
- MySQL Server
- MySQL JDBC Driver (mysql-connector-java)

## Setup Instructions

### 1. Database Setup
```sql
-- Run the database_schema.sql file in MySQL
mysql -u root -p < database_schema.sql
```

### 2. Update Database Configuration
Edit `DatabaseConnection.java` and update:
- URL: `jdbc:mysql://localhost:3306/library_db`
- USERNAME: Your MySQL username
- PASSWORD: Your MySQL password

### 3. Add MySQL JDBC Driver
Download MySQL Connector/J and add to classpath:
```bash
# Download from: https://dev.mysql.com/downloads/connector/j/
# Add to classpath when compiling and running
```

### 4. Compile and Run
```bash
# Navigate to project directory
cd lib_mag_system

# Compile (with MySQL driver in classpath)
javac -cp ".:mysql-connector-java-8.0.33.jar" src/*.java

# Run (with MySQL driver in classpath)
java -cp ".:mysql-connector-java-8.0.33.jar:src" LibraryManagement
```

## Usage

The system provides a console-based menu with the following options:

1. **Add Book** - Add new books to the library
2. **View Books** - Display all available books
3. **Add Student** - Register new students
4. **Issue Book** - Issue a book to a student
5. **Return Book** - Process book returns
6. **Search Books** - Search by title or author
7. **Exit** - Close the application

## Sample Operations

### Adding a Book
```
Enter book title: Clean Code
Enter author: Robert Martin
Enter publisher: Prentice Hall
Enter quantity: 3
```

### Issuing a Book
```
Enter student ID: S001
Enter book ID: 1
```

### Searching Books
```
Search by: 1. Title  2. Author
Enter choice: 1
Enter title to search: Java
```

## Database Schema

### Tables
- **books**: bookId, title, author, publisher, quantity
- **students**: studentId, name, course, year  
- **transactions**: transactionId, studentId, bookId, dateIssued, dueDate, returnDate

## Class Structure

```
src/
├── User.java              # Abstract base class
├── Person.java            # Base class for inheritance
├── Student.java           # Student entity (inherits Person)
├── Librarian.java         # Librarian entity (inherits User)
├── Book.java              # Book entity
├── DatabaseConnection.java # Database utility
├── LibraryDAO.java        # Data access layer
└── LibraryManagement.java # Main application class
```

## Future Enhancements

- GUI implementation using Swing/JavaFX
- Fine management system
- Book reservation system
- Advanced reporting features
- User authentication system