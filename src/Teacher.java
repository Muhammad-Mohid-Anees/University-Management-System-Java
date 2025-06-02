//import java.util.ArrayList;
//import java.util.List;
//
//public class Teacher {
//    private String name;
//    private String id;
//    private List<Course> courses;
//
//    public Teacher(String name, String id) {
//        this.name = name;
//        this.id = id;
//        this.courses = new ArrayList<>();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void addCourse(Course course) {
//        this.courses.add(course);
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//}


import java.util.ArrayList;
import java.util.List;

class Teacher extends Person {
    private List<Course> assignedCourses;

    public Teacher(int id, String name, String email, String department) {
        super(id, name, email, department);
        this.assignedCourses = new ArrayList<>();
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }

    public List<Course> getAssignedCourses() { return assignedCourses; }
}