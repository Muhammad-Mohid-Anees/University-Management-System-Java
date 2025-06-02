import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UniversityManagementSystemGUI extends JFrame {
    private DatabaseConnection db;
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
    private boolean isLoggedIn = false;

    public UniversityManagementSystemGUI() {
        db = new DatabaseConnection();
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

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(white);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        splitPane.setLeftComponent(null);
        splitPane.setRightComponent(contentPanel);
        add(splitPane, BorderLayout.CENTER);

        showSplashScreen();
    }

    private void initializeIcons() {
        try {
            umsLogo = loadIcon("images/student_id_icon.png", 32, 32);
            splashImage = loadIcon("images/building1.jpg", 1000, 600);
            backgroundImage = loadIcon("images/building2.jpg", 800, 600);
            functionIcons = new ImageIcon[]{
                    loadIcon("images/profile_refresh_icon.png", 32, 32), // DASHBOARD OVERVIEW
                    loadIcon("images/exam_note.png", 32, 32),           // ADD STUDENT
                    loadIcon("images/classroom.jpg", 32, 32),           // ADD TEACHER
                    loadIcon("images/fees_icon.png", 32, 32),           // ADD COURSE
                    loadIcon("images/refresh_icon.png", 32, 32),        // ENROLL STUDENT
                    loadIcon("images/verified_user_icon.png", 32, 32),  // ASSIGN TEACHER
                    loadIcon("images/settings_icon.png", 32, 32),       // ADD SCHEDULE
                    loadIcon("images/cap_coins.png", 32, 32),           // ADD FEE
                    loadIcon("images/student_id_icon.png", 32, 32),     // VIEW ALL STUDENTS
                    loadIcon("images/checklist_icon.png", 32, 32),      // VIEW ALL COURSES
                    loadIcon("images/search_icon.png", 32, 32),         // UPDATE ATTENDANCE
                    loadIcon("images/logout_icon.png", 32, 32),         // DELETE STUDENT
                    loadIcon("images/info_icon.png", 32, 32),           // RECOMMEND COURSES
                    loadIcon("images/calculator_icon.png", 32, 32),     // PERFORMANCE REPORT
                    loadIcon("images/education_jar.png", 32, 32),       // CHECK FEES
                    loadIcon("images/exit_icon.png", 32, 32),           // EXIT
                    loadIcon("images/refresh_icon.png", 32, 32),        // Placeholder
                    loadIcon("images/refresh_icon.png", 32, 32),        // Placeholder
                    loadIcon("images/refresh_icon.png", 32, 32),        // Placeholder
                    loadIcon("images/refresh_icon.png", 32, 32)         // Placeholder
            };
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            umsLogo = null;
            splashImage = null;
            backgroundImage = null;
            functionIcons = new ImageIcon[20];
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (splashImage != null) {
            g.drawImage(splashImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            System.err.println("Splash image is null - cannot draw");
            setBackground(Color.GRAY);  // Fallback color
        }
    }


    private ImageIcon loadIcon(String relativePath, int width, int height) {
        try {
            String basePath = "C:\\Users\\DELL\\IdeaProjects\\University Managment System\\";
            String fullPath = basePath + relativePath;
            System.out.println("Attempting to load image: " + fullPath); // Debug output
            File file = new File(fullPath);
            if (file.exists() && file.isFile()) {
                Image img = new ImageIcon(fullPath).getImage();
                if (img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                    System.err.println("Invalid image dimensions: " + fullPath);
                    return null;
                }
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImg);
                System.out.println("Successfully loaded image: " + fullPath + " (Size: " + width + "x" + height + ")");
                return icon;
            }
            System.err.println("Icon not found: " + fullPath);
            return null;
        } catch (Exception e) {
            System.err.println("Error loading icon from " + relativePath + ": " + e.getMessage());
            return null;
        }
    }

    private void styleSidebarButton(JButton button) {
        button.setBackground(white);
        button.setForeground(primaryBlue);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBorder(new LineBorder(primaryBlue, 1, true));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setPreferredSize(new Dimension(180, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(lightBlue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(white);
            }
        });
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(white);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(bgColor.darker(), 1, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(primaryBlue);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(new LineBorder(lightBlue, 1, true));
        field.setBackground(lightGray);
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBox.setBackground(lightGray);
        comboBox.setBorder(new LineBorder(lightBlue, 1, true));
    }

    private JPanel createStyledCard(String text, String iconPath) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(white);
        card.setBorder(new LineBorder(primaryBlue, 1, true));
        card.setPreferredSize(new Dimension(200, 100));

        ImageIcon icon = loadIcon("images/" + iconPath, 50, 50);
        JLabel iconLabel = new JLabel(icon != null ? icon : new ImageIcon());
        if (icon == null) {
            iconLabel.setText("Icon Missing");
            System.err.println("Failed to load icon for card: " + iconPath);
        }
        iconLabel.setBorder(new EmptyBorder(10, 10, 10, 0));

        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textLabel.setForeground(primaryBlue);

        card.add(iconLabel, BorderLayout.WEST);
        card.add(textLabel, BorderLayout.CENTER);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return card;
    }

    private JPanel createSectionTitle(String title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(white);
        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(primaryBlue);
        panel.add(label);
        return panel;
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
        if (umsLogo == null) {
            logoLabel.setText("Logo Missing");
            System.err.println("UMS Logo failed to load in sidebar");
        }
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
                "ENROLL STUDENT", "ASSIGN TEACHER", "ADD SCHEDULE", "ADD FEE",
                "VIEW ALL STUDENTS", "VIEW ALL COURSES", "UPDATE ATTENDANCE",
                "DELETE STUDENT", "RECOMMEND COURSES", "PERFORMANCE REPORT",
                "CHECK FEES", "EXIT"
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton button = new JButton(menuItems[i]);
            ImageIcon icon = functionIcons[i];
            if (icon != null) {
                button.setIcon(icon);
                button.setIconTextGap(8);
            } else {
                System.err.println("Icon missing for menu item: " + menuItems[i]);
            }
            styleSidebarButton(button);
            final String item = menuItems[i];
            button.addActionListener(e -> {
                if (item.equals("EXIT")) {
                    System.exit(0);
                } else {
                    updateContentPanel(item);
                }
            });
            sidebar.add(button, gbc);
            gbc.gridy++;
        }

        return sidebar;
    }

    private void showSplashScreen() {
        JPanel splashPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (splashImage != null) {
                    g.drawImage(splashImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                    System.out.println("Splash image drawn");
                } else {
                    System.err.println("Splash image is null - cannot draw");
                    setBackground(Color.RED); // Debug: make panel visible
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

        JLabel usernameLabel = new JLabel("Email:");
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
            String email = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            Admin admin = db.getAdmin(email, password);
            if (admin != null) {
                isLoggedIn = true;
                setTitle("University Management System - Admin Dashboard");
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
            case "ENROLL STUDENT": panel = createEnrollStudentPanel(); break;
            case "ASSIGN TEACHER": panel = createAssignTeacherPanel(); break;
            case "ADD SCHEDULE": panel = createAddSchedulePanel(); break;
            case "ADD FEE": panel = createAddFeePanel(); break;
            case "VIEW ALL STUDENTS": panel = createViewStudentsPanel(); break;
            case "VIEW ALL COURSES": panel = createViewCoursesPanel(); break;
            case "UPDATE ATTENDANCE": panel = createUpdateAttendancePanel(); break;
            case "DELETE STUDENT": panel = createDeleteStudentPanel(); break;
            case "RECOMMEND COURSES": panel = createRecommendCoursesPanel(); break;
            case "PERFORMANCE REPORT": panel = createPerformanceReportPanel(); break;
            case "CHECK FEES": panel = createCheckFeesPanel(); break;
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
                    System.out.println("Background image drawn on dashboard");
                } else {
                    System.err.println("Background image is null - cannot draw on dashboard");
                    setBackground(Color.YELLOW); // Debug: make panel visible
                }
            }
        };
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon logoIcon = loadIcon("images/graduation_scene.png", 250, 250);
        JLabel logoLabel = new JLabel(logoIcon != null ? logoIcon : new ImageIcon());
        if (logoIcon == null) {
            logoLabel.setText("Graduation Scene Missing");
            System.err.println("Graduation scene image failed to load in dashboard");
        }
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

        JPanel studentCard = createStyledCard(db.getAllStudents().size() + " Students", "student_notebook.jpg");
        studentCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateContentPanel("VIEW ALL STUDENTS");
            }
        });

        JPanel teacherCard = createStyledCard(db.getAllTeachers().size() + " Teachers", "classroom.jpg");
        teacherCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateContentPanel("ADD TEACHER");
            }
        });

        JPanel courseCard = createStyledCard(db.getAllCourses().size() + " Courses", "fees_icon.png");
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

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(15);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel deptLabel = new JLabel("Department:");
        JTextField deptField = new JTextField(15);
        JLabel gpaLabel = new JLabel("GPA:");
        JTextField gpaField = new JTextField(15);

        styleLabel(idLabel);
        styleLabel(nameLabel);
        styleLabel(emailLabel);
        styleLabel(deptLabel);
        styleLabel(gpaLabel);
        styleTextField(idField);
        styleTextField(nameField);
        styleTextField(emailField);
        styleTextField(deptField);
        styleTextField(gpaField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(deptLabel, gbc);
        gbc.gridx = 1;
        panel.add(deptField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(gpaLabel, gbc);
        gbc.gridx = 1;
        panel.add(gpaField, gbc);

        JButton addButton = new JButton("Add Student");
        styleButton(addButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String dept = deptField.getText().trim();
                double gpa = Double.parseDouble(gpaField.getText().trim());
                if (!name.isEmpty() && !email.isEmpty() && !dept.isEmpty()) {
                    db.addStudent(new Student(id, name, email, dept, gpa));
                    JOptionPane.showMessageDialog(this, "Student added successfully");
                    idField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    deptField.setText("");
                    gpaField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID or GPA", "Error", JOptionPane.ERROR_MESSAGE);
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

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(15);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel deptLabel = new JLabel("Department:");
        JTextField deptField = new JTextField(15);

        styleLabel(idLabel);
        styleLabel(nameLabel);
        styleLabel(emailLabel);
        styleLabel(deptLabel);
        styleTextField(idField);
        styleTextField(nameField);
        styleTextField(emailField);
        styleTextField(deptField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(deptLabel, gbc);
        gbc.gridx = 1;
        panel.add(deptField, gbc);

        JButton addButton = new JButton("Add Teacher");
        styleButton(addButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String dept = deptField.getText().trim();
                if (!name.isEmpty() && !email.isEmpty() && !dept.isEmpty()) {
                    db.addTeacher(new Teacher(id, name, email, dept));
                    JOptionPane.showMessageDialog(this, "Teacher added successfully");
                    idField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    deptField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
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

        JLabel idLabel = new JLabel("Course ID:");
        JTextField idField = new JTextField(15);
        JLabel nameLabel = new JLabel("Course Name:");
        JTextField nameField = new JTextField(15);
        JLabel deptLabel = new JLabel("Department:");
        JTextField deptField = new JTextField(15);
        JLabel classesLabel = new JLabel("Total Classes:");
        JTextField classesField = new JTextField(15);

        styleLabel(idLabel);
        styleLabel(nameLabel);
        styleLabel(deptLabel);
        styleLabel(classesLabel);
        styleTextField(idField);
        styleTextField(nameField);
        styleTextField(deptField);
        styleTextField(classesField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(deptLabel, gbc);
        gbc.gridx = 1;
        panel.add(deptField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(classesLabel, gbc);
        gbc.gridx = 1;
        panel.add(classesField, gbc);

        JButton addButton = new JButton("Add Course");
        styleButton(addButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String dept = deptField.getText().trim();
                int totalClasses = Integer.parseInt(classesField.getText().trim());
                if (!name.isEmpty() && !dept.isEmpty()) {
                    db.addCourse(new Course(id, name, dept, totalClasses));
                    JOptionPane.showMessageDialog(this, "Course added successfully");
                    idField.setText("");
                    nameField.setText("");
                    deptField.setText("");
                    classesField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID or Total Classes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createEnrollStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ENROLL STUDENT");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        List<Student> students = db.getAllStudents();
        for (Student s : students) {
            studentComboBox.addItem(s.getId() + " - " + s.getName());
        }
        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseComboBox = new JComboBox<>();
        List<Course> courses = db.getAllCourses();
        for (Course c : courses) {
            courseComboBox.addItem(c.getCourseId() + " - " + c.getCourseName());
        }

        styleLabel(studentLabel);
        styleLabel(courseLabel);
        styleComboBox(studentComboBox);
        styleComboBox(courseComboBox);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(courseLabel, gbc);
        gbc.gridx = 1;
        panel.add(courseComboBox, gbc);

        JButton enrollButton = new JButton("Enroll Student");
        styleButton(enrollButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(enrollButton, gbc);

        enrollButton.addActionListener(e -> {
            String studentSelection = (String) studentComboBox.getSelectedItem();
            String courseSelection = (String) courseComboBox.getSelectedItem();
            if (studentSelection != null && courseSelection != null) {
                int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                int courseId = Integer.parseInt(courseSelection.split(" - ")[0]);
                db.enrollStudent(studentId, courseId);
                JOptionPane.showMessageDialog(this, "Student enrolled successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student and course", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAssignTeacherPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ASSIGN TEACHER");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel teacherLabel = new JLabel("Teacher:");
        JComboBox<String> teacherComboBox = new JComboBox<>();
        List<Teacher> teachers = db.getAllTeachers();
        for (Teacher t : teachers) {
            teacherComboBox.addItem(t.getId() + " - " + t.getName());
        }
        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseComboBox = new JComboBox<>();
        List<Course> courses = db.getAllCourses();
        for (Course c : courses) {
            courseComboBox.addItem(c.getCourseId() + " - " + c.getCourseName());
        }

        styleLabel(teacherLabel);
        styleLabel(courseLabel);
        styleComboBox(teacherComboBox);
        styleComboBox(courseComboBox);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(teacherLabel, gbc);
        gbc.gridx = 1;
        panel.add(teacherComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(courseLabel, gbc);
        gbc.gridx = 1;
        panel.add(courseComboBox, gbc);

        JButton assignButton = new JButton("Assign Teacher");
        styleButton(assignButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(assignButton, gbc);

        assignButton.addActionListener(e -> {
            String teacherSelection = (String) teacherComboBox.getSelectedItem();
            String courseSelection = (String) courseComboBox.getSelectedItem();
            if (teacherSelection != null && courseSelection != null) {
                int teacherId = Integer.parseInt(teacherSelection.split(" - ")[0]);
                int courseId = Integer.parseInt(courseSelection.split(" - ")[0]);
                db.assignTeacher(teacherId, courseId);
                JOptionPane.showMessageDialog(this, "Teacher assigned successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a teacher and course", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddSchedulePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ADD SCHEDULE");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel idLabel = new JLabel("Schedule ID:");
        JTextField idField = new JTextField(15);
        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseComboBox = new JComboBox<>();
        List<Course> courses = db.getAllCourses();
        for (Course c : courses) {
            courseComboBox.addItem(c.getCourseId() + " - " + c.getCourseName());
        }
        JLabel roomLabel = new JLabel("Room:");
        JComboBox<String> roomComboBox = new JComboBox<>();
        List<Room> rooms = db.getAllRooms();
        for (Room r : rooms) {
            roomComboBox.addItem(r.getRoomNumber());
        }
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField(15);
        JLabel startTimeLabel = new JLabel("Start Time (HH:MM):");
        JTextField startTimeField = new JTextField(15);
        JLabel endTimeLabel = new JLabel("End Time (HH:MM):");
        JTextField endTimeField = new JTextField(15);

        styleLabel(idLabel);
        styleLabel(courseLabel);
        styleLabel(roomLabel);
        styleLabel(dateLabel);
        styleLabel(startTimeLabel);
        styleLabel(endTimeLabel);
        styleTextField(idField);
        styleComboBox(courseComboBox);
        styleComboBox(roomComboBox);
        styleTextField(dateField);
        styleTextField(startTimeField);
        styleTextField(endTimeField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(courseLabel, gbc);
        gbc.gridx = 1;
        panel.add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(roomLabel, gbc);
        gbc.gridx = 1;
        panel.add(roomComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(startTimeLabel, gbc);
        gbc.gridx = 1;
        panel.add(startTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(endTimeLabel, gbc);
        gbc.gridx = 1;
        panel.add(endTimeField, gbc);

        JButton addButton = new JButton("Add Schedule");
        styleButton(addButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                int scheduleId = Integer.parseInt(idField.getText().trim());
                String courseSelection = (String) courseComboBox.getSelectedItem();
                String roomNumber = (String) roomComboBox.getSelectedItem();
                String dateText = dateField.getText().trim();
                String startTimeText = startTimeField.getText().trim();
                String endTimeText = endTimeField.getText().trim();

                if (!dateText.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(this, "Date must be in YYYY-MM-DD format (e.g., 2025-06-02)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!startTimeText.matches("\\d{2}:\\d{2}") || !endTimeText.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(this, "Time must be in HH:MM format (24-hour, e.g., 18:06 for 6:06 PM)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalDate date = LocalDate.parse(dateText);
                LocalTime startTime = LocalTime.parse(startTimeText, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime endTime = LocalTime.parse(endTimeText, DateTimeFormatter.ofPattern("HH:mm"));

                if (courseSelection != null && roomNumber != null) {
                    int courseId = Integer.parseInt(courseSelection.split(" - ")[0]);
                    Course course = db.getAllCourses().stream().filter(c -> c.getCourseId() == courseId).findFirst().orElse(null);
                    Room room = new Room(roomNumber, 30);
                    Schedule schedule = new Schedule(scheduleId, course, room, date, startTime, endTime);
                    for (Schedule existing : db.getAllSchedules()) {
                        if (schedule.hasConflict(existing)) {
                            JOptionPane.showMessageDialog(this, "Schedule conflict detected!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    db.addSchedule(schedule);
                    JOptionPane.showMessageDialog(this, "Schedule added successfully");
                    idField.setText("");
                    dateField.setText("");
                    startTimeField.setText("");
                    endTimeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddFeePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("ADD FEE");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        List<Student> students = db.getAllStudents();
        for (Student s : students) {
            studentComboBox.addItem(s.getId() + " - " + s.getName());
        }
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(15);
        JLabel dueDateLabel = new JLabel("Due Date (YYYY-MM-DD):");
        JTextField dueDateField = new JTextField(15);

        styleLabel(studentLabel);
        styleLabel(amountLabel);
        styleLabel(dueDateLabel);
        styleComboBox(studentComboBox);
        styleTextField(amountField);
        styleTextField(dueDateField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(amountLabel, gbc);
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(dueDateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dueDateField, gbc);

        JButton addButton = new JButton("Add Fee");
        styleButton(addButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                String studentSelection = (String) studentComboBox.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText().trim());
                LocalDate dueDate = LocalDate.parse(dueDateField.getText().trim());
                if (studentSelection != null) {
                    int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                    db.addFee(new Fee(0, studentId, amount, dueDate));
                    JOptionPane.showMessageDialog(this, "Fee added successfully");
                    amountField.setText("");
                    dueDateField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a student", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            List<Student> students = db.getAllStudents();
            for (Student s : students) {
                sb.append("ID: ").append(s.getId())
                        .append(", Name: ").append(s.getName())
                        .append(", Email: ").append(s.getEmail())
                        .append(", Department: ").append(s.getDepartment())
                        .append(", GPA: ").append(s.getGpa()).append("\n");
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
            List<Course> courses = db.getAllCourses();
            for (Course c : courses) {
                sb.append("ID: ").append(c.getCourseId())
                        .append(", Name: ").append(c.getCourseName())
                        .append(", Department: ").append(c.getDepartment())
                        .append(", Total Classes: ").append(c.getTotalClasses())
                        .append(c.getTeacher() != null ? ", Teacher: " + c.getTeacher().getName() : "")
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
        List<Student> students = db.getAllStudents();
        for (Student s : students) {
            studentComboBox.addItem(s.getId() + " - " + s.getName());
        }
        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseComboBox = new JComboBox<>();
        List<Course> courses = db.getAllCourses();
        for (Course c : courses) {
            courseComboBox.addItem(c.getCourseId() + " - " + c.getCourseName());
        }
        JLabel daysLabel = new JLabel("Days Attended:");
        JTextField daysField = new JTextField(15);

        styleLabel(studentLabel);
        styleLabel(courseLabel);
        styleLabel(daysLabel);
        styleComboBox(studentComboBox);
        styleComboBox(courseComboBox);
        styleTextField(daysField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(courseLabel, gbc);
        gbc.gridx = 1;
        panel.add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(daysLabel, gbc);
        gbc.gridx = 1;
        panel.add(daysField, gbc);

        JButton updateButton = new JButton("Update Attendance");
        styleButton(updateButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                String studentSelection = (String) studentComboBox.getSelectedItem();
                String courseSelection = (String) courseComboBox.getSelectedItem();
                int daysAttended = Integer.parseInt(daysField.getText().trim());
                if (studentSelection != null && courseSelection != null) {
                    int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                    int courseId = Integer.parseInt(courseSelection.split(" - ")[0]);
                    db.updateAttendance(studentId, courseId, daysAttended);
                    JOptionPane.showMessageDialog(this, "Attendance updated successfully");
                    daysField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a student and course", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid days attended", "Error", JOptionPane.ERROR_MESSAGE);
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

        JLabel studentLabel = new JLabel("Student ID:");
        JTextField studentField = new JTextField(15);

        styleLabel(studentLabel);
        styleTextField(studentField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentField, gbc);

        JButton deleteButton = new JButton("Delete Student");
        styleButton(deleteButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentField.getText().trim());
                int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete student ID " + studentId + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    db.deleteStudent(studentId);
                    JOptionPane.showMessageDialog(this, "Student deleted successfully");
                    studentField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid student ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createRecommendCoursesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("RECOMMEND COURSES");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        List<Student> students = db.getAllStudents();
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

        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultArea.setBackground(lightGray);
        resultArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
        gbc.gridwidth = 1;
        gbc.weighty = 0;

        JButton recommendButton = new JButton("Recommend Courses");
        styleButton(recommendButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(recommendButton, gbc);

        recommendButton.addActionListener(e -> {
            String studentSelection = (String) studentComboBox.getSelectedItem();
            if (studentSelection != null) {
                try {
                    int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                    Student student = db.getAllStudents().stream()
                            .filter(s -> s.getId() == studentId)
                            .findFirst()
                            .orElse(null);
                    if (student != null) {
                        List<Course> enrolledCourses = db.getEnrolledCourses(studentId);
                        List<Course> deptCourses = db.getCoursesByDepartment(student.getDepartment());
                        List<Course> recommendedCourses = deptCourses.stream()
                                .filter(c -> !enrolledCourses.contains(c))
                                .collect(Collectors.toList());

                        StringBuilder sb = new StringBuilder();
                        if (recommendedCourses.isEmpty()) {
                            sb.append("No new courses available to recommend for ").append(student.getName()).append(".\n");
                        } else {
                            sb.append("Recommended Courses for ").append(student.getName()).append(" (").append(student.getDepartment()).append("):\n");
                            for (Course c : recommendedCourses) {
                                sb.append("- ").append(c.getCourseName()).append(" (ID: ").append(c.getCourseId()).append(")\n");
                            }
                        }
                        resultArea.setText(sb.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createPerformanceReportPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("PERFORMANCE REPORT");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        List<Student> students = db.getAllStudents();
        for (Student s : students) {
            studentComboBox.addItem(s.getId() + " - " + s.getName());
        }
        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseComboBox = new JComboBox<>();

        styleLabel(studentLabel);
        styleLabel(courseLabel);
        styleComboBox(studentComboBox);
        styleComboBox(courseComboBox);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(courseLabel, gbc);
        gbc.gridx = 1;
        panel.add(courseComboBox, gbc);

        JTextArea reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        reportArea.setBackground(lightGray);
        reportArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
        gbc.gridwidth = 1;
        gbc.weighty = 0;

        JButton generateButton = new JButton("Generate Report");
        styleButton(generateButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(generateButton, gbc);

        studentComboBox.addActionListener(e -> {
            courseComboBox.removeAllItems();
            String studentSelection = (String) studentComboBox.getSelectedItem();
            if (studentSelection != null) {
                try {
                    int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                    List<Course> enrolledCourses = db.getEnrolledCourses(studentId);
                    for (Course c : enrolledCourses) {
                        courseComboBox.addItem(c.getCourseId() + " - " + c.getCourseName());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        generateButton.addActionListener(e -> {
            String studentSelection = (String) studentComboBox.getSelectedItem();
            String courseSelection = (String) courseComboBox.getSelectedItem();
            if (studentSelection != null && courseSelection != null) {
                try {
                    int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                    int courseId = Integer.parseInt(courseSelection.split(" - ")[0]);
                    Student student = db.getAllStudents().stream()
                            .filter(s -> s.getId() == studentId)
                            .findFirst()
                            .orElse(null);
                    Course course = db.getAllCourses().stream()
                            .filter(c -> c.getCourseId() == courseId)
                            .findFirst()
                            .orElse(null);
                    if (student != null && course != null) {
                        int daysAttended = db.getAttendedDays(studentId, courseId);
                        double attendancePercentage = ((double) daysAttended / course.getTotalClasses()) * 100;
                        PerformanceReport report = new PerformanceReport(student, course, attendancePercentage);
                        db.savePerformanceReport(student, course, attendancePercentage);
                        reportArea.setText(report.generateReport());
                    } else {
                        JOptionPane.showMessageDialog(this, "Student or course not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid student or course ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student and course", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createCheckFeesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(white);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel headerPanel = createSectionTitle("CHECK FEES");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerPanel, gbc);
        gbc.gridwidth = 1;

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        List<Student> students = db.getAllStudents();
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

        JTextArea feeArea = new JTextArea(10, 30);
        feeArea.setEditable(false);
        feeArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        feeArea.setBackground(lightGray);
        feeArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(feeArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
        gbc.gridwidth = 1;
        gbc.weighty = 0;

        JButton checkButton = new JButton("Check Fees");
        styleButton(checkButton, primaryBlue);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(checkButton, gbc);

        checkButton.addActionListener(e -> {
            String studentSelection = (String) studentComboBox.getSelectedItem();
            if (studentSelection != null) {
                try {
                    int studentId = Integer.parseInt(studentSelection.split(" - ")[0]);
                    List<Fee> fees = db.getAllFees().stream()
                            .filter(f -> f.getStudentId() == studentId)
                            .collect(Collectors.toList());
                    StringBuilder sb = new StringBuilder();
                    if (fees.isEmpty()) {
                        sb.append("No fees recorded for student ID ").append(studentId).append(".\n");
                    } else {
                        sb.append("Fee Details for Student ID ").append(studentId).append(":\n");
                        for (Fee f : fees) {
                            try {
                                f.calculateLateFine(LocalDate.now());
                                sb.append(String.format("Fee ID: %d, Amount: $%.2f, Due Date: %s, Paid: %s, Late Fine: $%.2f\n",
                                        f.getFeeId(), f.getAmount(), f.getDueDate(), f.isPaid() ? "Yes" : "No", f.getLateFine()));
                            } catch (Exception feeEx) {
                                sb.append("Error calculating fee for Fee ID ").append(f.getFeeId()).append(": ").append(feeEx.getMessage()).append("\n");
                            }
                        }
                    }
                    feeArea.setText(sb.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Error setting look and feel: " + e.getMessage());
            }
            UniversityManagementSystemGUI gui = new UniversityManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}