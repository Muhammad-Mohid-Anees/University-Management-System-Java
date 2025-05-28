import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class University {
    private Connection connection;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    public University() {
        this.connection = DatabaseConnection.getConnection();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        try {
            String query = "INSERT INTO students (id, name, attendance) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setDouble(3, student.getAttendance());
            ps.executeUpdate();
            System.out.println("Student added to database: " + student.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        try {
            String query = "INSERT INTO teachers (id, name) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, teacher.getId());
            ps.setString(2, teacher.getName());
            ps.executeUpdate();
            System.out.println("Teacher added to database: " + teacher.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCourse(Course course) {
        courses.add(course);
        try {
            String query = "INSERT INTO courses (id, name, teacher_id) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, course.getId());
            ps.setString(2, course.getName());
            ps.setString(3, course.getTeacher() != null ? course.getTeacher().getId() : null);
            ps.executeUpdate();
            System.out.println("Course added to database: " + course.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try {
            String query = "SELECT * FROM students";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Student student = new Student(rs.getString("name"), rs.getString("id"));
                student.updateAttendance(rs.getDouble("attendance"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        try {
            String query = "SELECT * FROM teachers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("name"), rs.getString("id"));
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        try {
            String query = "SELECT * FROM courses";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Course course = new Course(rs.getString("name"), rs.getString("id"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public void updateStudentAttendance(String studentId, double attendance) {
        try {
            String query = "UPDATE students SET attendance = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, attendance);
            ps.setString(2, studentId);
            ps.executeUpdate();
            for (Student s : students) {
                if (s.getId().equals(studentId)) {
                    s.updateAttendance(attendance);
                    break;
                }
            }
            System.out.println("Updated attendance for student ID: " + studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(String studentId) {
        try {
            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, studentId);
            ps.executeUpdate();
            students.removeIf(s -> s.getId().equals(studentId));
            System.out.println("Deleted student ID: " + studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}