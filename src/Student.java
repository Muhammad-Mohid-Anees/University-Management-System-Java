import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String id;
    private double attendance;
    private List<Course> courses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.attendance = 0.0;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getAttendance() {
        return attendance;
    }

    public void updateAttendance(double attendance) {
        this.attendance = attendance;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
