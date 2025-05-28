import javax.swing.*;
import java.awt.*;

public class UniversityManagementGUI extends JFrame {
    private University university;

    public UniversityManagementGUI() {
        university = new University();

        setTitle("University Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("University Management System");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main Buttons Panel
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addStudentButton = createButton("Add Student");
        JButton addTeacherButton = createButton("Add Teacher");
        JButton addCourseButton = createButton("Add Course");
        JButton viewStudentsButton = createButton("View Students");
        JButton viewCoursesButton = createButton("View Courses");
        JButton updateAttendanceButton = createButton("Update Attendance");
        JButton deleteStudentButton = createButton("Delete Student");
        JButton exitButton = createButton("Exit");

        // Action Listeners
        addStudentButton.addActionListener(e -> showAddStudentDialog());
        addTeacherButton.addActionListener(e -> showAddTeacherDialog());
        addCourseButton.addActionListener(e -> showAddCourseDialog());
        viewStudentsButton.addActionListener(e -> showStudents());
        viewCoursesButton.addActionListener(e -> showCourses());
        updateAttendanceButton.addActionListener(e -> showUpdateAttendanceDialog());
        deleteStudentButton.addActionListener(e -> showDeleteStudentDialog());
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        mainPanel.add(addStudentButton);
        mainPanel.add(addTeacherButton);
        mainPanel.add(addCourseButton);
        mainPanel.add(viewStudentsButton);
        mainPanel.add(viewCoursesButton);
        mainPanel.add(updateAttendanceButton);
        mainPanel.add(deleteStudentButton);
        mainPanel.add(exitButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(0, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void showAddStudentDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter Student Name:");
        String id = JOptionPane.showInputDialog(this, "Enter Student ID:");

        if (name == null || id == null || name.trim().isEmpty() || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input. Name and ID are required.");
            return;
        }

        Student student = new Student(name.trim(), id.trim());
        university.addStudent(student);
        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    private void showAddTeacherDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter Teacher Name:");
        String id = JOptionPane.showInputDialog(this, "Enter Teacher ID:");

        if (name == null || id == null || name.trim().isEmpty() || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input. Name and ID are required.");
            return;
        }

        Teacher teacher = new Teacher(name.trim(), id.trim());
        university.addTeacher(teacher);
        JOptionPane.showMessageDialog(this, "Teacher added successfully!");
    }

    private void showAddCourseDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter Course Name:");
        String id = JOptionPane.showInputDialog(this, "Enter Course ID:");
        String teacherId = JOptionPane.showInputDialog(this, "Enter Teacher ID:");

        if (name == null || id == null || teacherId == null ||
                name.trim().isEmpty() || id.trim().isEmpty() || teacherId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        Teacher courseTeacher = null;
        for (Teacher t : university.getAllTeachers()) {
            if (t.getId().equals(teacherId.trim())) {
                courseTeacher = t;
                break;
            }
        }

        if (courseTeacher != null) {
            Course course = new Course(name.trim(), id.trim());
            course.setTeacher(courseTeacher);
            university.addCourse(course);
            JOptionPane.showMessageDialog(this, "Course added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Teacher not found!");
        }
    }

    private void showStudents() {
        StringBuilder studentList = new StringBuilder("List of Students:\n");
        for (Student s : university.getAllStudents()) {
            studentList.append("Name: ").append(s.getName())
                    .append(", ID: ").append(s.getId())
                    .append(", Attendance: ").append(s.getAttendance())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, studentList.toString());
    }

    private void showCourses() {
        StringBuilder courseList = new StringBuilder("List of Courses:\n");
        for (Course c : university.getAllCourses()) {
            courseList.append("Name: ").append(c.getName())
                    .append(", ID: ").append(c.getId());
            if (c.getTeacher() != null) {
                courseList.append(", Teacher: ").append(c.getTeacher().getName());
            } else {
                courseList.append(", No teacher assigned");
            }
            courseList.append("\n");
        }
        JOptionPane.showMessageDialog(this, courseList.toString());
    }

    private void showUpdateAttendanceDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
        String input = JOptionPane.showInputDialog(this, "Enter new attendance (%):");

        if (id == null || input == null || id.trim().isEmpty() || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
            return;
        }

        try {
            double attendance = Double.parseDouble(input);
            university.updateStudentAttendance(id.trim(), attendance);
            JOptionPane.showMessageDialog(this, "Attendance updated successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format.");
        }
    }

    private void showDeleteStudentDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");

        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student ID is required.");
            return;
        }

        university.deleteStudent(id.trim());
        JOptionPane.showMessageDialog(this, "Student deleted successfully.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UniversityManagementGUI gui = new UniversityManagementGUI();
            gui.setVisible(true);
        });
    }
}
