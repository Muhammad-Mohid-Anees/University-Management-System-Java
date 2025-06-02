//public class Course {
//    private String name;
//    private String id;
//    private Teacher teacher;
//
//    public Course(String name, String id) {
//        this.name = name;
//        this.id = id;
//        this.teacher = null;
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
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//}
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

class Course {
    private int courseId;
    private String courseName;
    private String department;
    private Teacher teacher;
    private List<Student> students;
    private int totalClasses;

    public Course(int courseId, String courseName, String department, int totalClasses) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.department = department;
        this.totalClasses = totalClasses;
        this.students = new ArrayList<>();
    }

    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getDepartment() { return department; }
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    public List<Student> getStudents() { return students; }
    public int getTotalClasses() { return totalClasses; }
    public void addStudent(Student student) { students.add(student); }
}