import java.util.List;

public class QuickDemo {
    public static void main(String[] args) {
        System.out.println("=== Library Management System - Quick Demo ===\n");
        
        MockLibraryDAO dao = new MockLibraryDAO();
        
        // Demonstrate OOP concepts
        System.out.println("1. INHERITANCE DEMO:");
        Student student = new Student("S001", "John Doe", "Computer Science", 2);
        System.out.println("   " + student);
        
        Librarian librarian = new Librarian("L001", "Admin", "Main Library");
        System.out.println("   Login successful: " + librarian.login("admin123"));
        
        System.out.println("\n2. ENCAPSULATION DEMO:");
        Book book = new Book(0, "Clean Code", "Robert Martin", "Prentice Hall", 3);
        System.out.println("   Book title: " + book.getTitle());
        book.setQuantity(5);
        System.out.println("   Updated quantity: " + book.getQuantity());
        
        System.out.println("\n3. POLYMORPHISM DEMO (Method Overloading):");
        List<Book> searchByTitle = dao.searchBooks("Java");
        System.out.println("   Search by title 'Java': " + searchByTitle.size() + " results");
        
        List<Book> searchByAuthor = dao.searchBooks("James", true);
        System.out.println("   Search by author 'James': " + searchByAuthor.size() + " results");
        
        System.out.println("\n4. ALL BOOKS IN LIBRARY:");
        List<Book> allBooks = dao.getAllBooks();
        for (Book b : allBooks) {
            System.out.println("   " + b);
        }
        
        System.out.println("\n5. BOOK OPERATIONS:");
        System.out.println("   Adding new book...");
        dao.addBook(book);
        System.out.println("   Total books now: " + dao.getAllBooks().size());
        
        System.out.println("   Issuing book ID 1 to student S001...");
        boolean issued = dao.issueBook("S001", 1);
        System.out.println("   Issue successful: " + issued);
        
        System.out.println("   Returning book ID 1...");
        boolean returned = dao.returnBook("S001", 1);
        System.out.println("   Return successful: " + returned);
        
        System.out.println("\n=== Demo Complete ===");
        System.out.println("All OOP concepts demonstrated:");
        System.out.println("✓ Encapsulation - Private fields with getters/setters");
        System.out.println("✓ Inheritance - Student extends Person, Librarian extends User");
        System.out.println("✓ Polymorphism - Method overloading in search functions");
        System.out.println("✓ Abstraction - Abstract User class with abstract login method");
    }
}