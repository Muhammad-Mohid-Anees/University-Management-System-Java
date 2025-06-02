////import javax.swing.*;
////import java.awt.*;
////
////public class UniversityManagementGUI extends JFrame {
////    private University university;
////
////    public UniversityManagementGUI() {
////        university = new University();
////
////        setTitle("University Management System");
////        setSize(600, 500);
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        setLocationRelativeTo(null);
////        setLayout(new BorderLayout());
////
////        // Header
////        JPanel headerPanel = new JPanel();
////        headerPanel.setBackground(new Color(0, 102, 204));
////        JLabel headerLabel = new JLabel("University Management System");
////        headerLabel.setForeground(Color.WHITE);
////        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
////        headerPanel.add(headerLabel);
////        add(headerPanel, BorderLayout.NORTH);
////
////        // Main Buttons Panel
////        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
////        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
////
////        JButton addStudentButton = createButton("Add Student");
////        JButton addTeacherButton = createButton("Add Teacher");
////        JButton addCourseButton = createButton("Add Course");
////        JButton viewStudentsButton = createButton("View Students");
////        JButton viewCoursesButton = createButton("View Courses");
////        JButton updateAttendanceButton = createButton("Update Attendance");
////        JButton deleteStudentButton = createButton("Delete Student");
////        JButton exitButton = createButton("Exit");
////
////        // Action Listeners
////        addStudentButton.addActionListener(e -> showAddStudentDialog());
////        addTeacherButton.addActionListener(e -> showAddTeacherDialog());
////        addCourseButton.addActionListener(e -> showAddCourseDialog());
////        viewStudentsButton.addActionListener(e -> showStudents());
////        viewCoursesButton.addActionListener(e -> showCourses());
////        updateAttendanceButton.addActionListener(e -> showUpdateAttendanceDialog());
////        deleteStudentButton.addActionListener(e -> showDeleteStudentDialog());
////        exitButton.addActionListener(e -> System.exit(0));
////
////        // Add buttons to panel
////        mainPanel.add(addStudentButton);
////        mainPanel.add(addTeacherButton);
////        mainPanel.add(addCourseButton);
////        mainPanel.add(viewStudentsButton);
////        mainPanel.add(viewCoursesButton);
////        mainPanel.add(updateAttendanceButton);
////        mainPanel.add(deleteStudentButton);
////        mainPanel.add(exitButton);
////
////        add(mainPanel, BorderLayout.CENTER);
////    }
////
////    private JButton createButton(String text) {
////        JButton button = new JButton(text);
////        button.setFont(new Font("Arial", Font.PLAIN, 16));
////        button.setBackground(new Color(0, 153, 255));
////        button.setForeground(Color.WHITE);
////        button.setFocusPainted(false);
////        return button;
////    }
////
////    private void showAddStudentDialog() {
////        String name = JOptionPane.showInputDialog(this, "Enter Student Name:");
////        String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
////
////        if (name == null || id == null || name.trim().isEmpty() || id.trim().isEmpty()) {
////            JOptionPane.showMessageDialog(this, "Invalid input. Name and ID are required.");
////            return;
////        }
////
////        Student student = new Student(name.trim(), id.trim());
////        university.addStudent(student);
////        JOptionPane.showMessageDialog(this, "Student added successfully!");
////    }
////
////    private void showAddTeacherDialog() {
////        String name = JOptionPane.showInputDialog(this, "Enter Teacher Name:");
////        String id = JOptionPane.showInputDialog(this, "Enter Teacher ID:");
////
////        if (name == null || id == null || name.trim().isEmpty() || id.trim().isEmpty()) {
////            JOptionPane.showMessageDialog(this, "Invalid input. Name and ID are required.");
////            return;
////        }
////
////        Teacher teacher = new Teacher(name.trim(), id.trim());
////        university.addTeacher(teacher);
////        JOptionPane.showMessageDialog(this, "Teacher added successfully!");
////    }
////
////    private void showAddCourseDialog() {
////        String name = JOptionPane.showInputDialog(this, "Enter Course Name:");
////        String id = JOptionPane.showInputDialog(this, "Enter Course ID:");
////        String teacherId = JOptionPane.showInputDialog(this, "Enter Teacher ID:");
////
////        if (name == null || id == null || teacherId == null ||
////                name.trim().isEmpty() || id.trim().isEmpty() || teacherId.trim().isEmpty()) {
////            JOptionPane.showMessageDialog(this, "All fields are required.");
////            return;
////        }
////
////        Teacher courseTeacher = null;
////        for (Teacher t : university.getAllTeachers()) {
////            if (t.getId().equals(teacherId.trim())) {
////                courseTeacher = t;
////                break;
////            }
////        }
////
////        if (courseTeacher != null) {
////            Course course = new Course(name.trim(), id.trim());
////            course.setTeacher(courseTeacher);
////            university.addCourse(course);
////            JOptionPane.showMessageDialog(this, "Course added successfully!");
////        } else {
////            JOptionPane.showMessageDialog(this, "Teacher not found!");
////        }
////    }
////
////    private void showStudents() {
////        StringBuilder studentList = new StringBuilder("List of Students:\n");
////        for (Student s : university.getAllStudents()) {
////            studentList.append("Name: ").append(s.getName())
////                    .append(", ID: ").append(s.getId())
////                    .append(", Attendance: ").append(s.getAttendance())
////                    .append("\n");
////        }
////        JOptionPane.showMessageDialog(this, studentList.toString());
////    }
////
////    private void showCourses() {
////        StringBuilder courseList = new StringBuilder("List of Courses:\n");
////        for (Course c : university.getAllCourses()) {
////            courseList.append("Name: ").append(c.getName())
////                    .append(", ID: ").append(c.getId());
////            if (c.getTeacher() != null) {
////                courseList.append(", Teacher: ").append(c.getTeacher().getName());
////            } else {
////                courseList.append(", No teacher assigned");
////            }
////            courseList.append("\n");
////        }
////        JOptionPane.showMessageDialog(this, courseList.toString());
////    }
////
////    private void showUpdateAttendanceDialog() {
////        String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
////        String input = JOptionPane.showInputDialog(this, "Enter new attendance (%):");
////
////        if (id == null || input == null || id.trim().isEmpty() || input.trim().isEmpty()) {
////            JOptionPane.showMessageDialog(this, "Invalid input.");
////            return;
////        }
////
////        try {
////            double attendance = Double.parseDouble(input);
////            university.updateStudentAttendance(id.trim(), attendance);
////            JOptionPane.showMessageDialog(this, "Attendance updated successfully!");
////        } catch (NumberFormatException e) {
////            JOptionPane.showMessageDialog(this, "Invalid number format.");
////        }
////    }
////
////    private void showDeleteStudentDialog() {
////        String id = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");
////
////        if (id == null || id.trim().isEmpty()) {
////            JOptionPane.showMessageDialog(this, "Student ID is required.");
////            return;
////        }
////
////        university.deleteStudent(id.trim());
////        JOptionPane.showMessageDialog(this, "Student deleted successfully.");
////    }
////
////    public static void main(String[] args) {
////        SwingUtilities.invokeLater(() -> {
////            UniversityManagementGUI gui = new UniversityManagementGUI();
////            gui.setVisible(true);
////        });
////    }
////}
//
//
//
//import javax.swing.*;
//import javax.swing.border.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.File;import javax.swing.*;
//import javax.swing.border.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
//import java.util.List;
//
//public class UniversityManagementSystemGUI extends JFrame {
//    private University university;
//    private JPanel contentPanel;
//    private final Color primaryBlue = new Color(30, 136, 229);
//    private final Color lightBlue = new Color(173, 216, 230);
//    private final Color white = new Color(255, 255, 255);
//    private final Color lightGray = new Color(244, 253, 253);
//    private ImageIcon umsLogo;
//    private ImageIcon splashImage;
//    private ImageIcon backgroundImage;
//    private ImageIcon[] functionIcons;
//
//    public UniversityManagementSystemGUI() {
//        university = new University();
//        initializeIcons();
//        setTitle("University Management System - Admin Dashboard");
//        setIconImage(umsLogo != null ? umsLogo.getImage() : null);
//        setSize(1000, 600);
//        setMinimumSize(new Dimension(800, 500));
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//        splitPane.setDividerSize(5);
//        splitPane.setBorder(null);
//        splitPane.setResizeWeight(0.2);
//        splitPane.setDividerLocation(200);
//
//        JPanel sidebar = createSidebar();
//        JScrollPane sidebarScroll = new JScrollPane(sidebar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        sidebarScroll.setBorder(null);
//        sidebarScroll.setPreferredSize(new Dimension(200, 0));
//
//        sidebarScroll.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
//            @Override
//            protected void configureScrollBarColors() {
//                this.trackColor = white;
//                this.thumbColor = new Color(200, 200, 200);
//            }
//
//            @Override
//            protected JButton createDecreaseButton(int orientation) {
//                return createEmptyButton();
//            }
//
//            @Override
//            protected JButton createIncreaseButton(int orientation) {
//                return createEmptyButton();
//            }
//
//            private JButton createEmptyButton() {
//                JButton button = new JButton();
//                button.setPreferredSize(new Dimension(0, 0));
//                button.setMinimumSize(new Dimension(0, 0));
//                button.setMaximumSize(new Dimension(0, 0));
//                return button;
//            }
//        });
//
//        contentPanel = new JPanel(new BorderLayout());
//        contentPanel.setBackground(white);
//        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        showSplashScreen();
//
//        splitPane.setLeftComponent(sidebarScroll);
//        splitPane.setRightComponent(contentPanel);
//        add(splitPane, BorderLayout.CENTER);
//    }
//
//    private void initializeIcons() {
//        try {
//            umsLogo = loadIcon("images/ums_logo.png", 32, 32);
//            splashImage = loadIcon("images/splash_image.png", 1000, 600);
//            backgroundImage = loadIcon("images/background_image.png", 800, 600);
//            functionIcons = new ImageIcon[]{
//                    loadIcon("images/add_student_icon.png", 32, 32),
//                    loadIcon("images/add_teacher_icon.png", 32, 32),
//                    loadIcon("images/add_course_icon.png", 32, 32),
//                    loadIcon("images/view_students_icon.png", 32, 32),
//                    loadIcon("images/view_courses_icon.png", 32, 32),
//                    loadIcon("images/update_attendance_icon.png", 32, 32),
//                    loadIcon("images/delete_student_icon.png", 32, 32),
//                    loadIcon("images/exit_icon.png", 32, 32)
//            };
//        } catch (Exception e) {
//            System.err.println("Error loading images: " + e.getMessage());
//            umsLogo = null;
//            splashImage = null;
//            backgroundImage = null;
//            functionIcons = new ImageIcon[8];
//        }
//    }
//
//    private JPanel createSidebar() {
//        JPanel sidebar = new JPanel(new GridBagLayout());
//        sidebar.setBackground(primaryBlue);
//        sidebar.setBorder(new EmptyBorder(10, 10, 10, 10));
//        sidebar.setMinimumSize(new Dimension(180, 0));
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(6, 6, 6, 6);
//        gbc.weightx = 1.0;
//
//        JLabel logoLabel = new JLabel(umsLogo != null ? umsLogo : new ImageIcon());
//        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        sidebar.add(logoLabel, gbc);
//        gbc.gridy++;
//
//        JLabel titleLabel = new JLabel("University Management System", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        titleLabel.setForeground(white);
//        sidebar.add(titleLabel, gbc);
//        gbc.gridy++;
//
//        String[] menuItems = {
//                "DASHBOARD OVERVIEW", "ADD STUDENT", "ADD TEACHER", "ADD COURSE",
//                "VIEW ALL STUDENTS", "VIEW ALL COURSES", "UPDATE ATTENDANCE", "DELETE STUDENT"
//        };
//
//        for (int i = 0; i < menuItems.length; i++) {
//            JButton button = new JButton(menuItems[i]);
//            ImageIcon icon = functionIcons[i];
//            if (icon != null) {
//                button.setIcon(icon);
//                button.setIconTextGap(8);
//            }
//            styleSidebarButton(button);
//            final String item = menuItems[i];
//            button.addActionListener(e -> updateContentPanel(item));
//            sidebar.add(button, gbc);
//            gbc.gridy++;
//        }
//
//        JButton logoutButton = new JButton("Exit");
//        ImageIcon logoutIcon = functionIcons[7];
//        if (logoutIcon != null) {
//            logoutButton.setIcon(logoutIcon);
//            logoutButton.setIconTextGap(8);
//        }
//        styleSidebarButton(logoutButton);
//        logoutButton.addActionListener(e -> System.exit(0));
//        sidebar.add(logoutButton, gbc);
//
//        return sidebar;
//    }
//
//    private void showSplashScreen() {
//        JPanel splashPanel = new JPanel(new BorderLayout()) {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                if (splashImage != null) {
//                    g.drawImage(splashImage.getImage(), 0, 0, getWidth(), getHeight(), this);
//                }
//            }
//        };
//        splashPanel.setBackground(white);
//        contentPanel.add(splashPanel, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//
//        Timer timer = new Timer(3000, e -> showLoginPanel());
//        timer.setRepeats(false);
//        timer.start();
//    }
//
//    private void showLoginPanel() {
//        contentPanel.removeAll();
//        JPanel loginPanel = new JPanel(new GridBagLayout());
//        loginPanel.setBackground(white);
//        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//
//        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
//        titleLabel.setForeground(primaryBlue);
//
//        JLabel usernameLabel = new JLabel("Username/Email:");
//        JTextField usernameField = new JTextField(15);
//        usernameField.setPreferredSize(new Dimension(200, 40));
//        JLabel passwordLabel = new JLabel("Password:");
//        JPasswordField passwordField = new JPasswordField(15);
//        passwordField.setPreferredSize(new Dimension(200, 40));
//        JButton loginButton = new JButton("Login");
//
//        styleLabel(usernameLabel);
//        styleLabel(passwordLabel);
//        styleTextField(usernameField);
//        styleTextField(passwordField);
//        styleButton(loginButton, primaryBlue);
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        loginPanel.add(titleLabel, gbc);
//
//        gbc.gridwidth = 1;
//        gbc.gridy = 1;
//        loginPanel.add(usernameLabel, gbc);
//        gbc.gridx = 1;
//        loginPanel.add(usernameField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        loginPanel.add(passwordLabel, gbc);
//        gbc.gridx = 1;
//        loginPanel.add(passwordField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        loginPanel.add(loginButton, gbc);
//
//        loginButton.addActionListener(e -> {
//            if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("password")) {
//                showDashboardOverview();
//            } else {
//                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        contentPanel.add(loginPanel, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//    }
//
//    private void updateContentPanel(String section) {
//        contentPanel.removeAll();
//        JPanel panel;
//        switch (section) {
//            case "DASHBOARD OVERVIEW":
//                panel = createDashboardOverviewPanel();
//                break;
//            case "ADD STUDENT":
//                panel = createAddStudentPanel();
//                break;
//            case "ADD TEACHER":
//                panel = createAddTeacherPanel();
//                break;
//            case "ADD COURSE":
//                panel = createAddCoursePanel();
//                break;
//            case "VIEW ALL STUDENTS":
//                panel = createViewStudentsPanel();
//                break;
//            case "VIEW ALL COURSES":
//                panel = createViewCoursesPanel();
//                break;
//            case "UPDATE ATTENDANCE":
//                panel = createUpdateAttendancePanel();
//                break;
//            case "DELETE STUDENT":
//                panel = createDeleteStudentPanel();
//                break;
//            default:
//                panel = new JPanel();
//        }
//
//        JPanel wrapper = new JPanel(new BorderLayout());
//        wrapper.setBackground(white);
//        wrapper.add(panel, BorderLayout.CENTER);
//
//        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        footerPanel.setBackground(white);
//        JLabel footerLabel = new JLabel("© 2025 University Management System");
//        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        footerLabel.setForeground(Color.GRAY);
//        footerPanel.add(footerLabel);
//        wrapper.add(footerPanel, BorderLayout.SOUTH);
//
//        contentPanel.add(wrapper, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//    }
//
//    private void showDashboardOverview() {
//        updateContentPanel("DASHBOARD OVERVIEW");
//    }
//
//    private JPanel createDashboardOverviewPanel() {
//        JPanel panel = new JPanel(new GridBagLayout()) {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                if (backgroundImage != null) {
//                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
//                }
//            }
//        };
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(6, 6, 6, 6);
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        ImageIcon logoIcon = loadIcon("images/main_logo.png", 250, 250);
//        JLabel logoLabel = new JLabel(logoIcon != null ? logoIcon : new ImageIcon());
//        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 3;
//        panel.add(logoLabel, gbc);
//
//        JLabel welcomeLabel = new JLabel("WELCOME TO UMS DASHBOARD", SwingConstants.CENTER);
//        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
//        welcomeLabel.setForeground(primaryBlue);
//        gbc.gridy = 1;
//        panel.add(welcomeLabel, gbc);
//        gbc.gridwidth = 1;
//
//        JPanel studentCard = createStyledCard(university.getAllStudents().size() + " Students", "student_icon.png");
//        studentCard.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                updateContentPanel("VIEW ALL STUDENTS");
//            }
//        });
//
//        JPanel teacherCard = createStyledCard(university.getAllTeachers().size() + " Teachers", "teacher_icon.png");
//        teacherCard.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                updateContentPanel("ADD TEACHER");
//            }
//        });
//
//        JPanel courseCard = createStyledCard(university.getAllCourses().size() + " Courses", "course_icon.png");
//        courseCard.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                updateContentPanel("VIEW ALL COURSES");
//            }
//        });
//
//        gbc.gridy = 2;
//        gbc.gridx = 0;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = 1.0;
//        panel.add(studentCard, gbc);
//        gbc.gridx = 1;
//        panel.add(teacherCard, gbc);
//        gbc.gridx = 2;
//        panel.add(courseCard, gbc);
//
//        return panel;
//    }
//
//    private JPanel createStyledCard(String text, String iconPath) {
//        JPanel card = new JPanel(new BorderLayout());
//        card.setBackground(primaryBlue);
//        card.setOpaque(true);
//        card.setBorder(BorderFactory.createCompoundBorder(
//                new RoundedBorder(primaryBlue, 1, 8),
//                new EmptyBorder(10, 10, 10, 10)
//        ));
//        card.setPreferredSize(new Dimension(200, 80));
//        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        JPanel shadowPanel = new JPanel(new BorderLayout());
//        shadowPanel.setBackground(white);
//        shadowPanel.setBorder(new ShadowBorder(5));
//        shadowPanel.add(card, BorderLayout.CENTER);
//
//        ImageIcon icon = loadIcon("images/" + iconPath, 50, 50);
//        JLabel iconLabel = new JLabel(icon != null ? icon : new ImageIcon());
//        iconLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
//        card.add(iconLabel, BorderLayout.WEST);
//
//        JLabel textLabel = new JLabel(text);
//        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
//        textLabel.setForeground(white);
//        textLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
//        card.add(textLabel, BorderLayout.CENTER);
//
//        return shadowPanel;
//    }
//
//    private static class RoundedBorder implements Border {
//        private final Color color;
//        private final int thickness;
//        private final int radius;
//
//        public RoundedBorder(Color color, int thickness, int radius) {
//            this.color = color;
//            this.thickness = thickness;
//            this.radius = radius;
//        }
//
//        @Override
//        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//            Graphics2D g2 = (Graphics2D) g.create();
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.setColor(color);
//            g2.setStroke(new BasicStroke(thickness));
//            g2.drawRoundRect(x, y, width - thickness, height - thickness, radius, radius);
//            g2.dispose();
//        }
//
//        @Override
//        public Insets getBorderInsets(Component c) {
//            return new Insets(thickness, thickness, thickness, thickness);
//        }
//
//        @Override
//        public boolean isBorderOpaque() {
//            return false;
//        }
//    }
//
//    private static class ShadowBorder implements Border {
//        private final int shadowSize;
//
//        public ShadowBorder(int shadowSize) {
//            this.shadowSize = shadowSize;
//        }
//
//        @Override
//        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//            Graphics2D g2 = (Graphics2D) g.create();
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            Color shadowColor = new Color(0, 57, 106, 255);
//            g2.setColor(shadowColor);
//            g2.fillRoundRect(x + shadowSize, y + shadowSize, width - shadowSize, height - shadowSize, 8, 8);
//            g2.dispose();
//        }
//
//        @Override
//        public Insets getBorderInsets(Component c) {
//            return new Insets(shadowSize, shadowSize, shadowSize * 2, shadowSize * 2);
//        }
//
//        @Override
//        public boolean isBorderOpaque() {
//            return false;
//        }
//    }
//
//    private JPanel createAddStudentPanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBackground(white);
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JPanel headerPanel = createSectionTitle("ADD STUDENT");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        panel.add(headerPanel, gbc);
//        gbc.gridwidth = 1;
//
//        JLabel nameLabel = new JLabel("Name:");
//        JTextField nameField = new JTextField(15);
//        nameField.setPreferredSize(new Dimension(200, 40));
//        JLabel idLabel = new JLabel("ID:");
//        JTextField idField = new JTextField(15);
//        idField.setPreferredSize(new Dimension(200, 40));
//
//        styleLabel(nameLabel);
//        styleLabel(idLabel);
//        styleTextField(nameField);
//        styleTextField(idField);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        panel.add(nameLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(nameField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(idLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(idField, gbc);
//
//        JButton addButton = new JButton("Add Student");
//        styleButton(addButton, primaryBlue);
//        addButton.setPreferredSize(new Dimension(200, 40));
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.anchor = GridBagConstraints.EAST;
//        panel.add(addButton, gbc);
//
//        addButton.addActionListener(e -> {
//            String name = nameField.getText().trim();
//            String id = idField.getText().trim();
//            if (!name.isEmpty() && !id.isEmpty()) {
//                try {
//                    Student student = new Student(name, id);
//                    university.addStudent(student);
//                    JOptionPane.showMessageDialog(this, "Student added successfully");
//                    nameField.setText("");
//                    idField.setText("");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Name and ID are required", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        return panel;
//    }
//
//    private JPanel createAddTeacherPanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBackground(white);
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JPanel headerPanel = createSectionTitle("ADD TEACHER");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        panel.add(headerPanel, gbc);
//        gbc.gridwidth = 1;
//
//        JLabel nameLabel = new JLabel("Name:");
//        JTextField nameField = new JTextField(15);
//        nameField.setPreferredSize(new Dimension(200, 40));
//        JLabel idLabel = new JLabel("ID:");
//        JTextField idField = new JTextField(15);
//        idField.setPreferredSize(new Dimension(200, 40));
//
//        styleLabel(nameLabel);
//        styleLabel(idLabel);
//        styleTextField(nameField);
//        styleTextField(idField);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        panel.add(nameLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(nameField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(idLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(idField, gbc);
//
//        JButton addButton = new JButton("Add Teacher");
//        styleButton(addButton, primaryBlue);
//        addButton.setPreferredSize(new Dimension(200, 40));
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.anchor = GridBagConstraints.EAST;
//        panel.add(addButton, gbc);
//
//        addButton.addActionListener(e -> {
//            String name = nameField.getText().trim();
//            String id = idField.getText().trim();
//            if (!name.isEmpty() && !id.isEmpty()) {
//                try {
//                    Teacher teacher = new Teacher(name, id);
//                    university.addTeacher(teacher);
//                    JOptionPane.showMessageDialog(this, "Teacher added successfully");
//                    nameField.setText("");
//                    idField.setText("");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(this, "Error adding teacher: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Name and ID are required", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        return panel;
//    }
//
//    private JPanel createAddCoursePanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBackground(white);
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JPanel headerPanel = createSectionTitle("ADD COURSE");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        panel.add(headerPanel, gbc);
//        gbc.gridwidth = 1;
//
//        JLabel nameLabel = new JLabel("Course Name:");
//        JTextField nameField = new JTextField(15);
//        nameField.setPreferredSize(new Dimension(200, 40));
//        JLabel idLabel = new JLabel("Course ID:");
//        JTextField idField = new JTextField(15);
//        idField.setPreferredSize(new Dimension(200, 40));
//        JLabel teacherLabel = new JLabel("Teacher:");
//        JComboBox<String> teacherComboBox = new JComboBox<>();
//        teacherComboBox.setPreferredSize(new Dimension(200, 40));
//        List<Teacher> teachers = university.getAllTeachers();
//        for (Teacher t : teachers) {
//            teacherComboBox.addItem(t.getId() + " - " + t.getName());
//        }
//
//        styleLabel(nameLabel);
//        styleLabel(idLabel);
//        styleLabel(teacherLabel);
//        styleTextField(nameField);
//        styleTextField(idField);
//        styleComboBox(teacherComboBox);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        panel.add(nameLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(nameField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(idLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(idField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        panel.add(teacherLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(teacherComboBox, gbc);
//
//        JButton addButton = new JButton("Add Course");
//        styleButton(addButton, primaryBlue);
//        addButton.setPreferredSize(new Dimension(200, 40));
//        gbc.gridx = 1;
//        gbc.gridy = 4;
//        gbc.anchor = GridBagConstraints.EAST;
//        panel.add(addButton, gbc);
//
//        addButton.addActionListener(e -> {
//            String name = nameField.getText().trim();
//            String id = idField.getText().trim();
//            String teacherSelection = (String) teacherComboBox.getSelectedItem();
//            if (!name.isEmpty() && !id.isEmpty() && teacherSelection != null) {
//                try {
//                    String teacherId = teacherSelection.split(" - ")[0];
//                    Teacher teacher = null;
//                    for (Teacher t : teachers) {
//                        if (t.getId().equals(teacherId)) {
//                            teacher = t;
//                            break;
//                        }
//                    }
//                    if (teacher != null) {
//                        Course course = new Course(name, id);
//                        course.setTeacher(teacher);
//                        university.addCourse(course);
//                        JOptionPane.showMessageDialog(this, "Course added successfully");
//                        nameField.setText("");
//                        idField.setText("");
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Teacher not found", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(this, "Error adding course: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        return panel;
//    }
//
//    private JPanel createViewStudentsPanel() {
//        JPanel panel = new JPanel(new BorderLayout(10, 10));
//        panel.setBackground(white);
//
//        JPanel titlePanel = createSectionTitle("VIEW ALL STUDENTS");
//        panel.add(titlePanel, BorderLayout.NORTH);
//
//        JTextArea area = new JTextArea();
//        area.setEditable(false);
//        area.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        area.setBackground(lightGray);
//        area.setBorder(new EmptyBorder(8, 8, 8, 8));
//        JScrollPane scrollPane = new JScrollPane(area);
//        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        JButton loadButton = new JButton("Load Students");
//        styleButton(loadButton, primaryBlue);
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        buttonPanel.setBackground(white);
//        buttonPanel.add(loadButton);
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        loadButton.addActionListener(e -> {
//            StringBuilder sb = new StringBuilder();
//            List<Student> students = university.getAllStudents();
//            for (Student s : students) {
//                sb.append("Name: ").append(s.getName())
//                        .append(", ID: ").append(s.getId())
//                        .append(", Attendance: ").append(s.getAttendance()).append("%\n");
//            }
//            area.setText(sb.toString());
//        });
//
//        return panel;
//    }
//
//    private JPanel createViewCoursesPanel() {
//        JPanel panel = new JPanel(new BorderLayout(10, 10));
//        panel.setBackground(white);
//
//        JPanel titlePanel = createSectionTitle("VIEW ALL COURSES");
//        panel.add(titlePanel, BorderLayout.NORTH);
//
//        JTextArea area = new JTextArea();
//        area.setEditable(false);
//        area.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        area.setBackground(lightGray);
//        area.setBorder(new EmptyBorder(8, 8, 8, 8));
//        JScrollPane scrollPane = new JScrollPane(area);
//        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        JButton loadButton = new JButton("Load Courses");
//        styleButton(loadButton, primaryBlue);
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        buttonPanel.setBackground(white);
//        buttonPanel.add(loadButton);
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        loadButton.addActionListener(e -> {
//            StringBuilder sb = new StringBuilder();
//            List<Course> courses = university.getAllCourses();
//            for (Course c : courses) {
//                sb.append("Name: ").append(c.getName())
//                        .append(", ID: ").append(c.getId())
//                        .append(c.getTeacher() != null ? ", Teacher: " + c.getTeacher().getName() : ", No teacher assigned")
//                        .append("\n");
//            }
//            area.setText(sb.toString());
//        });
//
//        return panel;
//    }
//
//    private JPanel createUpdateAttendancePanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBackground(white);
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JPanel headerPanel = createSectionTitle("UPDATE ATTENDANCE");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        panel.add(headerPanel, gbc);
//        gbc.gridwidth = 1;
//
//        JLabel studentLabel = new JLabel("Student:");
//        JComboBox<String> studentComboBox = new JComboBox<>();
//        studentComboBox.setPreferredSize(new Dimension(200, 40));
//        List<Student> students = university.getAllStudents();
//        for (Student s : students) {
//            studentComboBox.addItem(s.getId() + " - " + s.getName());
//        }
//        JLabel attendanceLabel = new JLabel("Attendance (%):");
//        JTextField attendanceField = new JTextField(10);
//        attendanceField.setPreferredSize(new Dimension(200, 40));
//
//        styleLabel(studentLabel);
//        styleLabel(attendanceLabel);
//        styleComboBox(studentComboBox);
//        styleTextField(attendanceField);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        panel.add(studentLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(studentComboBox, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(attendanceLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(attendanceField, gbc);
//
//        JButton updateButton = new JButton("Update Attendance");
//        styleButton(updateButton, primaryBlue);
//        updateButton.setPreferredSize(new Dimension(200, 40));
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.anchor = GridBagConstraints.EAST;
//        panel.add(updateButton, gbc);
//
//        updateButton.addActionListener(e -> {
//            String studentSelection = (String) studentComboBox.getSelectedItem();
//            String attendanceText = attendanceField.getText().trim();
//            if (studentSelection != null && !attendanceText.isEmpty()) {
//                try {
//                    double attendance = Double.parseDouble(attendanceText);
//                    String studentId = studentSelection.split(" - ")[0];
//                    university.updateStudentAttendance(studentId, attendance);
//                    JOptionPane.showMessageDialog(this, "Attendance updated successfully");
//                    attendanceField.setText("");
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(this, "Invalid attendance value", "Error", JOptionPane.ERROR_MESSAGE);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(this, "Error updating attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        return panel;
//    }
//
//    private JPanel createDeleteStudentPanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBackground(white);
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JPanel headerPanel = createSectionTitle("DELETE STUDENT");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        panel.add(headerPanel, gbc);
//        gbc.gridwidth = 1;
//
//        JLabel studentLabel = new JLabel("Student:");
//        JComboBox<String> studentComboBox = new JComboBox<>();
//        studentComboBox.setPreferredSize(new Dimension(200, 40));
//        List<Student> students = university.getAllStudents();
//        for (Student s : students) {
//            studentComboBox.addItem(s.getId() + " - " + s.getName());
//        }
//
//        styleLabel(studentLabel);
//        styleComboBox(studentComboBox);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        panel.add(studentLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(studentComboBox, gbc);
//
//        JButton deleteButton = new JButton("Delete Student");
//        styleButton(deleteButton, primaryBlue);
//        deleteButton.setPreferredSize(new Dimension(200, 40));
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        gbc.anchor = GridBagConstraints.EAST;
//        panel.add(deleteButton, gbc);
//
//        deleteButton.addActionListener(e -> {
//            String studentSelection = (String) studentComboBox.getSelectedItem();
//            if (studentSelection != null) {
//                try {
//                    String studentId = studentSelection.split(" - ")[0];
//                    university.deleteStudent(studentId);
//                    JOptionPane.showMessageDialog(this, "Student deleted successfully");
//                    studentComboBox.removeItem(studentSelection);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(this, "Error deleting student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Please select a student", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        return panel;
//    }
//
//    private JPanel createSectionTitle(String text) {
//        JPanel titlePanel = new JPanel(new BorderLayout());
//        titlePanel.setBackground(primaryBlue);
//        titlePanel.setPreferredSize(new Dimension(320, 40));
//        titlePanel.setBorder(BorderFactory.createLineBorder(primaryBlue, 1));
//
//        JLabel titleLabel = new JLabel(text, SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
//        titleLabel.setForeground(white);
//        titlePanel.add(titleLabel, BorderLayout.CENTER);
//
//        return titlePanel;
//    }
//
//    private void styleTextField(JTextField field) {
//        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        field.setBorder(BorderFactory.createCompoundBorder(
//                new LineBorder(new Color(200, 200, 200), 1, false),
//                new EmptyBorder(6, 8, 6, 8)
//        ));
//        field.setBackground(white);
//
//        field.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                field.setBorder(BorderFactory.createCompoundBorder(
//                        new LineBorder(primaryBlue, 1, false),
//                        new EmptyBorder(6, 8, 6, 8)
//                ));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                field.setBorder(BorderFactory.createCompoundBorder(
//                        new LineBorder(new Color(200, 200, 200), 1, false),
//                        new EmptyBorder(6, 8, 6, 8)
//                ));
//            }
//        });
//    }
//
//    private void styleButton(JButton button, Color color) {
//        button.setBackground(color);
//        button.setForeground(white);
//        button.setFocusPainted(false);
//        button.setBorderPainted(false);
//        button.setOpaque(true);
//        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        button.setBorder(new EmptyBorder(6, 8, 6, 8));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        button.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                button.setBackground(new Color(50, 100, 200));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                button.setBackground(color);
//            }
//        });
//    }
//
//    private void styleSidebarButton(JButton button) {
//        button.setBackground(primaryBlue);
//        button.setForeground(white);
//        button.setFocusPainted(false);
//        button.setBorderPainted(false);
//        button.setOpaque(true);
//        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        button.setBorder(new EmptyBorder(6, 8, 6, 8));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.setHorizontalAlignment(SwingConstants.LEFT);
//
//        button.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                button.setBackground(new Color(50, 100, 200));
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                button.setBackground(new Color(25, 75, 175));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                button.setBackground(new Color(50, 100, 200));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                button.setBackground(primaryBlue);
//            }
//        });
//    }
//
//    private void styleLabel(JLabel label) {
//        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        label.setForeground(Color.BLACK);
//    }
//
//    private void styleComboBox(JComboBox<String> comboBox) {
//        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        comboBox.setBackground(white);
//        comboBox.setBorder(BorderFactory.createCompoundBorder(
//                new LineBorder(new Color(200, 200, 200), 1, false),
//                new EmptyBorder(6, 8, 6, 8)
//        ));
//    }
//
//    private ImageIcon loadIcon(String relativePath, int width, int height) {
//        try {
//            String basePath = "D:\\Semester 3\\OOP\\semester project\\university_management_system\\";
//            String fullPath = basePath + relativePath;
//            File file = new File(fullPath);
//            if (file.exists() && file.isFile()) {
//                Image img = new ImageIcon(file.getAbsolutePath()).getImage();
//                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//                return new ImageIcon(scaledImg);
//            }
//            System.err.println("Icon not found: " + fullPath);
//            return null;
//        } catch (Exception e) {
//            System.err.println("Error loading icon from " + relativePath + ": " + e.getMessage());
//            return null;
//        }
//    }
//}




import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;


public class UniversityManagementSystemGUI extends JFrame {
    private University university;
    private JPanel contentPanel;
    private JSplitPane splitPane;
    private final Color primaryBlue = new Color(30, 136, 229);
    private final Color lightBlue = new Color(173, 216, 230);
    private final Color white = new Color(255, 255, 255);
    private final Color lightGray = new Color(244, 253, 253);
    private ImageIcon umsLogo;
    private ImageIcon splashImage;
    private ImageIcon backgroundImage;
    private ImageIcon[] functionIcons;
    private boolean isLoggedIn = false; // Track login state

    public UniversityManagementSystemGUI() {
        university = new University();
        initializeIcons();
        setTitle("University Management System - Admin Login");
        setIconImage(umsLogo != null ? umsLogo.getImage() : null);
        setSize(1000, 600);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(5);
        splitPane.setBorder(null);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerLocation(200);

        JPanel sidebar = createSidebar();
        JScrollPane sidebarScroll = new JScrollPane(sidebar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sidebarScroll.setBorder(null);
        sidebarScroll.setPreferredSize(new Dimension(200, 0));

        sidebarScroll.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = white;
                this.thumbColor = new Color(200, 200, 200);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createEmptyButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createEmptyButton();
            }

            private JButton createEmptyButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(white);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Hide sidebar until login
        splitPane.setLeftComponent(null);
        splitPane.setRightComponent(contentPanel);
        add(splitPane, BorderLayout.CENTER);

        showSplashScreen();
    }

    private void initializeIcons() {
        try {
            umsLogo = loadIcon("images/ums_logo.png", 32, 32);
            splashImage = loadIcon("images/splash_image.png", 1000, 600);
            backgroundImage = loadIcon("images/background_image.png", 800, 600);
            functionIcons = new ImageIcon[]{
                    loadIcon("images/add_student_icon.png", 32, 32),
                    loadIcon("images/add_teacher_icon.png", 32, 32),
                    loadIcon("images/add_course_icon.png", 32, 32),
                    loadIcon("images/view_students_icon.png", 32, 32),
                    loadIcon("images/view_courses_icon.png", 32, 32),
                    loadIcon("images/update_attendance_icon.png", 32, 32),
                    loadIcon("images/delete_student_icon.png", 32, 32),
                    loadIcon("images/exit_icon.png", 32, 32)
            };
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            umsLogo = null;
            splashImage = null;
            backgroundImage = null;
            functionIcons = new ImageIcon[8];
        }
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(primaryBlue);
        sidebar.setBorder(new EmptyBorder(10, 10, 10, 10));
        sidebar.setMinimumSize(new Dimension(180, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.weightx = 1.0;

        JLabel logoLabel = new JLabel(umsLogo != null ? umsLogo : new ImageIcon());
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidebar.add(logoLabel, gbc);
        gbc.gridy++;

        JLabel titleLabel = new JLabel("University Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(white);
        sidebar.add(titleLabel, gbc);
        gbc.gridy++;

        String[] menuItems = {
                "DASHBOARD OVERVIEW", "ADD STUDENT", "ADD TEACHER", "ADD COURSE",
                "VIEW ALL STUDENTS", "VIEW ALL COURSES", "UPDATE ATTENDANCE", "DELETE STUDENT"
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton button = new JButton(menuItems[i]);
            ImageIcon icon = functionIcons[i];
            if (icon != null) {
                button.setIcon(icon);
                button.setIconTextGap(8);
            }
            styleSidebarButton(button);
            final String item = menuItems[i];
            button.addActionListener(e -> updateContentPanel(item));
            sidebar.add(button, gbc);
            gbc.gridy++;
        }

        JButton logoutButton = new JButton("Exit");
        ImageIcon logoutIcon = functionIcons[7];
        if (logoutIcon != null) {
            logoutButton.setIcon(logoutIcon);
            logoutButton.setIconTextGap(8);
        }
        styleSidebarButton(logoutButton);
        logoutButton.addActionListener(e -> System.exit(0));
        sidebar.add(logoutButton, gbc);

        return sidebar;
    }

    private void showSplashScreen() {
        JPanel splashPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (splashImage != null) {
                    g.drawImage(splashImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        splashPanel.setBackground(white);
        contentPanel.add(splashPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        Timer timer = new Timer(3000, e -> showLoginPanel());
        timer.setRepeats(false);
        timer.start();
    }

    private void showLoginPanel() {
        contentPanel.removeAll();
        setTitle("University Management System - Admin Login");
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(white);
        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Admin Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(primaryBlue);

        JLabel usernameLabel = new JLabel("Username/Email:");
        JTextField usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(200, 40));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(200, 40));
        JButton loginButton = new JButton("Login");

        styleLabel(usernameLabel);
        styleLabel(passwordLabel);
        styleTextField(usernameField);
        styleTextField(passwordField);
        styleButton(loginButton, primaryBlue);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("password")) {
                isLoggedIn = true;
                setTitle("University Management System - Admin Dashboard");
                // Show sidebar after login
                JPanel sidebar = createSidebar();
                JScrollPane sidebarScroll = new JScrollPane(sidebar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                sidebarScroll.setBorder(null);
                splitPane.setLeftComponent(sidebarScroll);
                showDashboardOverview();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        contentPanel.add(loginPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void updateContentPanel(String section) {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(this, "Please log in to access this feature", "Access Denied", JOptionPane.WARNING_MESSAGE);
            showLoginPanel();
            return;
        }

        contentPanel.removeAll();
        JPanel panel;
        switch (section) {
            case "DASHBOARD OVERVIEW": panel = createDashboardOverviewPanel(); break;
            case "ADD STUDENT": panel = createAddStudentPanel(); break;
            case "ADD TEACHER": panel = createAddTeacherPanel(); break;
            case "ADD COURSE": panel = createAddCoursePanel(); break;
            case "VIEW ALL STUDENTS": panel = createViewStudentsPanel(); break;
            case "VIEW ALL COURSES": panel = createViewCoursesPanel(); break;
            case "UPDATE ATTENDANCE": panel = createUpdateAttendancePanel(); break;
            case "DELETE STUDENT": panel = createDeleteStudentPanel(); break;
            default: panel = new JPanel();
        }

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(white);
        wrapper.add(panel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(white);
        JLabel footerLabel = new JLabel("© 2025 University Management System");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(Color.GRAY);
        footerPanel.add(footerLabel);
        wrapper.add(footerPanel, BorderLayout.SOUTH);

        contentPanel.add(wrapper, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showDashboardOverview() {
        if (!isLoggedIn) {
            showLoginPanel();
            return;
        }
        updateContentPanel("DASHBOARD OVERVIEW");
    }

    private JPanel createDashboardOverviewPanel() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon logoIcon = loadIcon("images/main_logo.png", 250, 250);
        JLabel logoLabel = new JLabel(logoIcon != null ? logoIcon : new ImageIcon());
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(logoLabel, gbc);

        JLabel welcomeLabel = new JLabel("WELCOME TO UMS DASHBOARD", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(primaryBlue);
        gbc.gridy = 1;
        panel.add(welcomeLabel, gbc);
        gbc.gridwidth = 1;

        JPanel studentCard = createStyledCard(university.getAllStudents().size() + " Students", "student_icon.png");
        studentCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateContentPanel("VIEW ALL STUDENTS");
            }
        });

        JPanel teacherCard = createStyledCard(university.getAllTeachers().size() + " Teachers", "teacher_icon.png");
        teacherCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateContentPanel("ADD TEACHER");
            }
        });

        JPanel courseCard = createStyledCard(university.getAllCourses().size() + " Courses", "course_icon.png");
        courseCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateContentPanel("VIEW ALL COURSES");
            }
        });

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        panel.add(studentCard, gbc);
        gbc.gridx = 1;
        panel.add(teacherCard, gbc);
        gbc.gridx = 2;
        panel.add(courseCard, gbc);

        return panel;
    }

    private JPanel createStyledCard(String text, String iconPath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(primaryBlue);
        card.setOpaque(true);
        card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(primaryBlue, 1, 8),
                new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(200, 80));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel shadowPanel = new JPanel(new BorderLayout());
        shadowPanel.setBackground(white);
        shadowPanel.setBorder(new ShadowBorder(5));
        shadowPanel.add(card, BorderLayout.CENTER);

        ImageIcon icon = loadIcon("images/" + iconPath, 50, 50);
        JLabel iconLabel = new JLabel(icon != null ? icon : new ImageIcon());
        iconLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        card.add(iconLabel, BorderLayout.WEST);

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        textLabel.setForeground(white);
        textLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        card.add(textLabel, BorderLayout.CENTER);

        return shadowPanel;
    }

    private static class RoundedBorder implements Border {
        private final Color color;
        private final int thickness;
        private final int radius;

        public RoundedBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width - thickness, height - thickness, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    private static class ShadowBorder implements Border {
        private final int shadowSize;

        public ShadowBorder(int shadowSize) {
            this.shadowSize = shadowSize;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color shadowColor = new Color(0, 57, 106, 255);
            g2.setColor(shadowColor);
            g2.fillRoundRect(x + shadowSize, y + shadowSize, width - shadowSize, height - shadowSize, 8, 8);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(shadowSize, shadowSize, shadowSize * 2, shadowSize * 2);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ADD STUDENT");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 40));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(15);
        idField.setPreferredSize(new Dimension(200, 40));

        styleLabel(nameLabel);
        styleLabel(idLabel);
        styleTextField(nameField);
        styleTextField(idField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        JButton addButton = new JButton("Add Student");
        styleButton(addButton, primaryBlue);
        addButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            if (!name.isEmpty() && !id.isEmpty()) {
                try {
                    Student student = new Student(name, id);
                    university.addStudent(student);
                    JOptionPane.showMessageDialog(this, "Student added successfully");
                    nameField.setText("");
                    idField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Name and ID are required", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddTeacherPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ADD TEACHER");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 40));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(15);
        idField.setPreferredSize(new Dimension(200, 40));

        styleLabel(nameLabel);
        styleLabel(idLabel);
        styleTextField(nameField);
        styleTextField(idField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        JButton addButton = new JButton("Add Teacher");
        styleButton(addButton, primaryBlue);
        addButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            if (!name.isEmpty() && !id.isEmpty()) {
                try {
                    Teacher teacher = new Teacher(name, id);
                    university.addTeacher(teacher);
                    JOptionPane.showMessageDialog(this, "Teacher added successfully");
                    nameField.setText("");
                    idField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error adding teacher: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Name and ID are required", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddCoursePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ADD COURSE");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel nameLabel = new JLabel("Course Name:");
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 40));
        JLabel idLabel = new JLabel("Course ID:");
        JTextField idField = new JTextField(15);
        idField.setPreferredSize(new Dimension(200, 40));
        JLabel teacherLabel = new JLabel("Teacher:");
        JComboBox<String> teacherComboBox = new JComboBox<>();
        teacherComboBox.setPreferredSize(new Dimension(200, 40));
        List<Teacher> teachers = university.getAllTeachers();
        for (Teacher t : teachers) {
            teacherComboBox.addItem(t.getId() + " - " + t.getName());
        }

        styleLabel(nameLabel);
        styleLabel(idLabel);
        styleLabel(teacherLabel);
        styleTextField(nameField);
        styleTextField(idField);
        styleComboBox(teacherComboBox);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(teacherLabel, gbc);
        gbc.gridx = 1;
        panel.add(teacherComboBox, gbc);

        JButton addButton = new JButton("Add Course");
        styleButton(addButton, primaryBlue);
        addButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String teacherSelection = (String) teacherComboBox.getSelectedItem();
            if (!name.isEmpty() && !id.isEmpty() && teacherSelection != null) {
                try {
                    String teacherId = teacherSelection.split(" - ")[0];
                    Teacher teacher = null;
                    for (Teacher t : teachers) {
                        if (t.getId().equals(teacherId)) {
                            teacher = t;
                            break;
                        }
                    }
                    if (teacher != null) {
                        Course course = new Course(name, id);
                        course.setTeacher(teacher);
                        university.addCourse(course);
                        JOptionPane.showMessageDialog(this, "Course added successfully");
                        nameField.setText("");
                        idField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Teacher not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error adding course: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createViewStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(white);

        JPanel titlePanel = createSectionTitle("VIEW ALL STUDENTS");
        panel.add(titlePanel, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        area.setBackground(lightGray);
        area.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Students");
        styleButton(loadButton, primaryBlue);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(white);
        buttonPanel.add(loadButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            List<Student> students = university.getAllStudents();
            for (Student s : students) {
                sb.append("Name: ").append(s.getName())
                        .append(", ID: ").append(s.getId())
                        .append(", Attendance: ").append(s.getAttendance()).append("%\n");
            }
            area.setText(sb.toString());
        });

        return panel;
    }

    private JPanel createViewCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(white);

        JPanel titlePanel = createSectionTitle("VIEW ALL COURSES");
        panel.add(titlePanel, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        area.setBackground(lightGray);
        area.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Courses");
        styleButton(loadButton, primaryBlue);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(white);
        buttonPanel.add(loadButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            List<Course> courses = university.getAllCourses();
            for (Course c : courses) {
                sb.append("Name: ").append(c.getName())
                        .append(", ID: ").append(c.getId())
                        .append(c.getTeacher() != null ? ", Teacher: " + c.getTeacher().getName() : ", No teacher assigned")
                        .append("\n");
            }
            area.setText(sb.toString());
        });

        return panel;
    }

    private JPanel createUpdateAttendancePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("UPDATE ATTENDANCE");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        studentComboBox.setPreferredSize(new Dimension(200, 40));
        List<Student> students = university.getAllStudents();
        for (Student s : students) {
            studentComboBox.addItem(s.getId() + " - " + s.getName());
        }
        JLabel attendanceLabel = new JLabel("Attendance (%):");
        JTextField attendanceField = new JTextField(10);
        attendanceField.setPreferredSize(new Dimension(200, 40));

        styleLabel(studentLabel);
        styleLabel(attendanceLabel);
        styleComboBox(studentComboBox);
        styleTextField(attendanceField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(attendanceLabel, gbc);
        gbc.gridx = 1;
        panel.add(attendanceField, gbc);

        JButton updateButton = new JButton("Update Attendance");
        styleButton(updateButton, primaryBlue);
        updateButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            String studentSelection = (String) studentComboBox.getSelectedItem();
            String attendanceText = attendanceField.getText().trim();
            if (studentSelection != null && !attendanceText.isEmpty()) {
                try {
                    double attendance = Double.parseDouble(attendanceText);
                    String studentId = studentSelection.split(" - ")[0];
                    university.updateStudentAttendance(studentId, attendance);
                    JOptionPane.showMessageDialog(this, "Attendance updated successfully");
                    attendanceField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid attendance value", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createDeleteStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("DELETE STUDENT");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        studentComboBox.setPreferredSize(new Dimension(200, 40));
        List<Student> students = university.getAllStudents();
        for (Student s : students) {
            studentComboBox.addItem(s.getId() + " - " + s.getName());
        }

        styleLabel(studentLabel);
        styleComboBox(studentComboBox);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        JButton deleteButton = new JButton("Delete Student");
        styleButton(deleteButton, primaryBlue);
        deleteButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            String studentSelection = (String) studentComboBox.getSelectedItem();
            if (studentSelection != null) {
                try {
                    String studentId = studentSelection.split(" - ")[0];
                    university.deleteStudent(studentId);
                    JOptionPane.showMessageDialog(this, "Student deleted successfully");
                    studentComboBox.removeItem(studentSelection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createSectionTitle(String text) {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(primaryBlue);
        titlePanel.setPreferredSize(new Dimension(320, 40));
        titlePanel.setBorder(BorderFactory.createLineBorder(primaryBlue, 1));

        JLabel titleLabel = new JLabel(text, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(white);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        return titlePanel;
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, false),
                new EmptyBorder(6, 8, 6, 8)
        ));
        field.setBackground(white);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(primaryBlue, 1, false),
                        new EmptyBorder(6, 8, 6, 8)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(200, 200, 200), 1, false),
                        new EmptyBorder(6, 8, 6, 8)
                ));
            }
        });
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(white);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBorder(new EmptyBorder(6, 8, 6, 8));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(50, 100, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }

    private void styleSidebarButton(JButton button) {
        button.setBackground(primaryBlue);
        button.setForeground(white);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(new EmptyBorder(6, 8, 6, 8));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(50, 100, 200));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(25, 75, 175));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(50, 100, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryBlue);
            }
        });
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(Color.BLACK);
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBox.setBackground(white);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, false),
                new EmptyBorder(6, 8, 6, 8)
        ));
    }

    private ImageIcon loadIcon(String relativePath, int width, int height) {
        try {
            String basePath = "D:\\Semester 3\\OOP\\semester project\\university_management_system\\";
            String fullPath = basePath + relativePath;
            File file = new File(fullPath);
            if (file.exists() && file.isFile()) {
                Image img = new ImageIcon(file.getAbsolutePath()).getImage();
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            }
            System.err.println("Icon not found: " + fullPath);
            return null;
        } catch (Exception e) {
            System.err.println("Error loading icon from " + relativePath + ": " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UniversityManagementSystemGUI gui = new UniversityManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}