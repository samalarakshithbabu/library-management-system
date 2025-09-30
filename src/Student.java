// Student class demonstrating Inheritance
public class Student extends Person {
    private String course;
    private int year;
    
    public Student(String studentId, String name, String course, int year) {
        super(studentId, name);
        this.course = course;
        this.year = year;
    }
    
    // Getters and Setters (Encapsulation)
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    @Override
    public String toString() {
        return "Student{ID='" + id + "', Name='" + name + "', Course='" + course + "', Year=" + year + "}";
    }
}