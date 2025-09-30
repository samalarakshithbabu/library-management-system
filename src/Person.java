// Base class for inheritance
public class Person {
    protected String name;
    protected String id;
    
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters (Encapsulation)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}