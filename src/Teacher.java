import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private String id;
    private List<Course> courses;

    public Teacher(String name, String id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}