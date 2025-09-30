import java.util.ArrayList;
import java.util.List;

// Mock DAO for demonstration without database
public class MockLibraryDAO {
    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private int nextBookId = 1;
    
    public MockLibraryDAO() {
        // Sample data
        books.add(new Book(nextBookId++, "Java Programming", "James Gosling", "Oracle Press", 5));
        books.add(new Book(nextBookId++, "Database Systems", "Ramez Elmasri", "Pearson", 3));
        books.add(new Book(nextBookId++, "Data Structures", "Robert Lafore", "Sams Publishing", 4));
    }
    
    // Method overloading for search (Polymorphism)
    public List<Book> searchBooks(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
    
    public List<Book> searchBooks(String author, boolean isAuthor) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
    
    public boolean addBook(Book book) {
        book.setBookId(nextBookId++);
        books.add(book);
        return true;
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    
    public boolean addStudent(Student student) {
        students.add(student);
        return true;
    }
    
    public boolean issueBook(String studentId, int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId && book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                return true;
            }
        }
        return false;
    }
    
    public boolean returnBook(String studentId, int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.setQuantity(book.getQuantity() + 1);
                return true;
            }
        }
        return false;
    }
}