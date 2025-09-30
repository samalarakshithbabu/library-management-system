-- MySQL Database Schema for Library Management System

-- Create database
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

-- Books table
CREATE TABLE books (
    bookId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    quantity INT NOT NULL DEFAULT 0
);

-- Students table
CREATE TABLE students (
    studentId VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    course VARCHAR(100) NOT NULL,
    year INT NOT NULL
);

-- Transactions table for issue/return records
CREATE TABLE transactions (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    studentId VARCHAR(50) NOT NULL,
    bookId INT NOT NULL,
    dateIssued DATE NOT NULL,
    dueDate DATE NOT NULL,
    returnDate DATE NULL,
    FOREIGN KEY (studentId) REFERENCES students(studentId),
    FOREIGN KEY (bookId) REFERENCES books(bookId)
);

-- Sample data insertion
INSERT INTO books (title, author, publisher, quantity) VALUES
('Java Programming', 'James Gosling', 'Oracle Press', 5),
('Database Systems', 'Ramez Elmasri', 'Pearson', 3),
('Data Structures', 'Robert Lafore', 'Sams Publishing', 4),
('Operating Systems', 'Abraham Silberschatz', 'Wiley', 2);

INSERT INTO students (studentId, name, course, year) VALUES
('S001', 'John Doe', 'Computer Science', 2),
('S002', 'Jane Smith', 'Information Technology', 3),
('S003', 'Mike Johnson', 'Software Engineering', 1);