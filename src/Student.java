//import java.util.ArrayList;
//import java.util.List;
//
//public class Student {
//    private String name;
//    private String id;
//    private double attendance;
//    private List<Course> courses;
//
//    public Student(String name, String id) {
//        this.name = name;
//        this.id = id;
//        this.attendance = 0.0;
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
//    public double getAttendance() {
//        return attendance;
//    }
//
//    public void updateAttendance(double attendance) {
//        this.attendance = attendance;
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

// Student class
class Student extends Person {
    private double gpa;
    private List<Course> enrolledCourses;
    private Map<Course, Integer> attendance;

    public Student(int id, String name, String email, String department, double gpa) {
        super(id, name, email, department);
        this.gpa = gpa;
        this.enrolledCourses = new ArrayList<>();
        this.attendance = new HashMap<>();
    }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public Map<Course, Integer> getAttendance() { return attendance; }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        attendance.put(course, 0);
    }

    public void updateAttendance(Course course, int daysAttended) {
        attendance.put(course, attendance.getOrDefault(course, 0) + daysAttended);
    }

    public double getAttendancePercentage(Course course, int totalClasses) {
        return ((double) attendance.getOrDefault(course, 0) / totalClasses) * 100;
    }
}