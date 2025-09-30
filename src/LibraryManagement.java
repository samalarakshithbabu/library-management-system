import java.util.List;
import java.util.Scanner;

public class LibraryManagement {
    private static LibraryDAO dao = new LibraryDAO();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Library Management System ===");
        
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    searchBooks();
                    break;
                case 7:
                    System.out.println("Thank you for using Library Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("\n--- Library Management Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Add Student");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Search Books");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        Book book = new Book(0, title, author, publisher, quantity);
        if (dao.addBook(book)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Failed to add book!");
        }
    }
    
    private static void viewBooks() {
        List<Book> books = dao.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            System.out.println("\n--- All Books ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    
    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter course: ");
        String course = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        
        Student student = new Student(studentId, name, course, year);
        if (dao.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student!");
        }
    }
    
    private static void issueBook() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        if (dao.issueBook(studentId, bookId)) {
            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Failed to issue book! Book may not be available.");
        }
    }
    
    private static void returnBook() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        if (dao.returnBook(studentId, bookId)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Failed to return book!");
        }
    }
    
    private static void searchBooks() {
        System.out.println("Search by: 1. Title  2. Author");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        List<Book> books;
        if (choice == 1) {
            System.out.print("Enter title to search: ");
            String title = scanner.nextLine();
            books = dao.searchBooks(title); // Polymorphism - method overloading
        } else {
            System.out.print("Enter author to search: ");
            String author = scanner.nextLine();
            books = dao.searchBooks(author, true); // Polymorphism - method overloading
        }
        
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            System.out.println("\n--- Search Results ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}