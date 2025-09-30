import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Data Access Object class demonstrating Polymorphism with method overloading
public class LibraryDAO {
    
    // Method overloading for search (Polymorphism)
    public List<Book> searchBooks(String title) {
        return searchBooksByTitle(title);
    }
    
    public List<Book> searchBooks(String author, boolean isAuthor) {
        return searchBooksByAuthor(author);
    }
    
    private List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    private List<Book> searchBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + author + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (title, author, publisher, quantity) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getQuantity());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (studentId, name, course, year) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getYear());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean issueBook(String studentId, int bookId) {
        String checkSql = "SELECT quantity FROM books WHERE bookId = ?";
        String updateSql = "UPDATE books SET quantity = quantity - 1 WHERE bookId = ?";
        String insertSql = "INSERT INTO transactions (studentId, bookId, dateIssued, dueDate) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY))";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            
            // Check availability
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, bookId);
                ResultSet rs = checkStmt.executeQuery();
                
                if (rs.next() && rs.getInt("quantity") > 0) {
                    // Update book quantity
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, bookId);
                        updateStmt.executeUpdate();
                    }
                    
                    // Insert transaction
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, studentId);
                        insertStmt.setInt(2, bookId);
                        insertStmt.executeUpdate();
                    }
                    
                    conn.commit();
                    return true;
                }
            }
            conn.rollback();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean returnBook(String studentId, int bookId) {
        String updateBookSql = "UPDATE books SET quantity = quantity + 1 WHERE bookId = ?";
        String updateTransactionSql = "UPDATE transactions SET returnDate = CURDATE() WHERE studentId = ? AND bookId = ? AND returnDate IS NULL";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            
            try (PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql);
                 PreparedStatement updateTransactionStmt = conn.prepareStatement(updateTransactionSql)) {
                
                updateBookStmt.setInt(1, bookId);
                updateTransactionStmt.setString(1, studentId);
                updateTransactionStmt.setInt(2, bookId);
                
                updateBookStmt.executeUpdate();
                int rowsAffected = updateTransactionStmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                }
            }
            conn.rollback();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}