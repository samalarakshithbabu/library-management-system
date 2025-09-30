// Librarian class demonstrating Inheritance
public class Librarian extends User {
    private String department;
    
    public Librarian(String librarianId, String name, String department) {
        super(librarianId, name);
        this.department = department;
    }
    
    @Override
    public boolean login(String password) {
        // Simple password check for demo
        return "admin123".equals(password);
    }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}