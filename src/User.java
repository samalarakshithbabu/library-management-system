// Abstract base class demonstrating Abstraction
public abstract class User {
    protected String userId;
    protected String name;
    
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    
    // Abstract method for login - must be implemented by subclasses
    public abstract boolean login(String password);
    
    // Getters and Setters (Encapsulation)
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}