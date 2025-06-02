import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

// DatabaseConnection class (updated with missing methods)
class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/university_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Mohid.1234"; // Update with your MySQL password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (id, name, email, department, gpa) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getDepartment());
            stmt.setDouble(5, student.getGpa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public void addTeacher(Teacher teacher) {
        String query = "INSERT INTO teachers (id, name, email, department) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, teacher.getId());
            stmt.setString(2, teacher.getName());
            stmt.setString(3, teacher.getEmail());
            stmt.setString(4, teacher.getDepartment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding teacher: " + e.getMessage());
        }
    }

    public void addCourse(Course course) {
        String query = "INSERT INTO courses (course_id, course_name, department, total_classes) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDepartment());
            stmt.setInt(4, course.getTotalClasses());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    public void enrollStudent(int studentId, int courseId) {
        String query = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error enrolling student: " + e.getMessage());
        }
    }

    public void assignTeacher(int teacherId, int courseId) {
        String query = "UPDATE courses SET teacher_id = ? WHERE course_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, teacherId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error assigning teacher: " + e.getMessage());
        }
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO rooms (room_number, capacity) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setInt(2, room.getCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
        }
    }

    public void addSchedule(Schedule schedule) {
        String query = "INSERT INTO schedules (schedule_id, course_id, room_number, schedule_date, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, schedule.getScheduleId());
            stmt.setInt(2, schedule.getCourse().getCourseId());
            stmt.setString(3, schedule.getRoom().getRoomNumber());
            stmt.setDate(4, Date.valueOf(schedule.getDate()));
            stmt.setTime(5, Time.valueOf(schedule.getStartTime()));
            stmt.setTime(6, Time.valueOf(schedule.getEndTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding schedule: " + e.getMessage());
        }
    }

    public void addFee(Fee fee) {
        String query = "INSERT INTO fees (student_id, amount, due_date, paid, late_fine) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, fee.getStudentId());
            stmt.setDouble(2, fee.getAmount());
            stmt.setDate(3, Date.valueOf(fee.getDueDate()));
            stmt.setBoolean(4, fee.isPaid());
            stmt.setDouble(5, fee.getLateFine());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                try {
                    java.lang.reflect.Field field = Fee.class.getDeclaredField("feeId");
                    field.setAccessible(true);
                    field.set(fee, rs.getInt(1));
                } catch (Exception e) {
                    System.err.println("Error setting feeId: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding fee: " + e.getMessage());
        }
    }

    public void updateAttendance(int studentId, int courseId, int daysAttended) {
        String query = "INSERT INTO attendance (student_id, course_id, days_attended) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE days_attended = days_attended + ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setInt(3, daysAttended);
            stmt.setInt(4, daysAttended);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating attendance: " + e.getMessage());
        }
    }

    public void deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getDouble("gpa")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching teachers: " + e.getMessage());
        }
        return teachers;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("department"),
                        rs.getInt("total_classes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching courses: " + e.getMessage());
        }
        return courses;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getString("room_number"),
                        rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rooms: " + e.getMessage());
        }
        return rooms;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT s.*, c.course_id, c.course_name, c.department, c.total_classes " +
                "FROM schedules s JOIN courses c ON s.course_id = c.course_id";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("department"),
                        rs.getInt("total_classes")
                );
                Room room = new Room(rs.getString("room_number"), 30); // Default capacity
                schedules.add(new Schedule(
                        rs.getInt("schedule_id"),
                        course,
                        room,
                        rs.getDate("schedule_date").toLocalDate(),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching schedules: " + e.getMessage());
        }
        return schedules;
    }

    public List<Fee> getAllFees() {
        List<Fee> fees = new ArrayList<>();
        String query = "SELECT * FROM fees";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                fees.add(new Fee(
                        rs.getInt("fee_id"),
                        rs.getInt("student_id"),
                        rs.getDouble("amount"),
                        rs.getDate("due_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching fees: " + e.getMessage());
        }
        return fees;
    }

    public List<Course> getCoursesByDepartment(String department) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses WHERE department = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, department);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("department"),
                        rs.getInt("total_classes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching courses: " + e.getMessage());
        }
        return courses;
    }

    public Admin getAdmin(String email, String password) {
        String query = "SELECT * FROM admins WHERE email = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching admin: " + e.getMessage());
        }
        return null;
    }

    // Added missing method: getEnrolledCourses
    public List<Course> getEnrolledCourses(int studentId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.* FROM courses c JOIN enrollments e ON c.course_id = e.course_id WHERE e.student_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("department"),
                        rs.getInt("total_classes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching enrolled courses: " + e.getMessage());
        }
        return courses;
    }

    // Added missing method: getAttendanceDays
    public int getAttendanceDays(int studentId, int courseId) {
        String query = "SELECT days_attended FROM attendance WHERE student_id = ? AND course_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("days_attended");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance: " + e.getMessage());
        }
        return 0;
    }

    // Added missing method: savePerformanceReport
    public void savePerformanceReport(Student student, Course course, double attendancePercentage) {
        String query = "INSERT INTO performance_reports (student_id, course_id, attendance_percentage, predicted_grade, report_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            PerformanceReport report = new PerformanceReport(student, course, attendancePercentage);
            stmt.setInt(1, student.getId());
            stmt.setInt(2, course.getCourseId());
            stmt.setDouble(3, attendancePercentage);
            stmt.setDouble(4, report.predictedGrade);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving performance report: " + e.getMessage());
        }
    }

    public int getAttendedDays(int studentId, int courseId) {
        return 0;
    }
}
