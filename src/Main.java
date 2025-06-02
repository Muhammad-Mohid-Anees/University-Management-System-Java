//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        University university = new University(); // Use updated University class
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n=== University Management System ===");
//            System.out.println("1. Add Student");
//            System.out.println("2. Add Teacher");
//            System.out.println("3. Add Course");
//            System.out.println("4. View All Students");
//            System.out.println("5. View All Courses");
//            System.out.println("6. Update Student Attendance");
//            System.out.println("7. Delete Student");
//            System.out.println("8. Exit");
//            System.out.print("Choose an option: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter student name: ");
//                    String studentName = scanner.nextLine();
//                    System.out.print("Enter student ID: ");
//                    String studentId = scanner.nextLine();
//
//                    if (studentName.isEmpty() || studentId.isEmpty()) {
//                        System.out.println("Invalid input. Name and ID are required.");
//                        break;
//                    }
//
//                    Student student = new Student(studentName, studentId);
//                    university.addStudent(student);
//                    break;
//
//                case 2:
//                    System.out.print("Enter teacher name: ");
//                    String teacherName = scanner.nextLine();
//                    System.out.print("Enter teacher ID: ");
//                    String teacherId = scanner.nextLine();
//
//                    if (teacherName.isEmpty() || teacherId.isEmpty()) {
//                        System.out.println("Invalid input. Name and ID are required.");
//                        break;
//                    }
//
//                    Teacher teacher = new Teacher(teacherName, teacherId);
//                    university.addTeacher(teacher);
//                    break;
//
//                case 3:
//                    System.out.print("Enter course name: ");
//                    String courseName = scanner.nextLine();
//                    System.out.print("Enter course ID: ");
//                    String courseId = scanner.nextLine();
//                    System.out.print("Enter teacher ID for this course: ");
//                    String courseTeacherId = scanner.nextLine();
//
//                    Teacher courseTeacher = null;
//                    for (Teacher t : university.getAllTeachers()) {
//                        if (t.getId().equals(courseTeacherId)) {
//                            courseTeacher = t;
//                            break;
//                        }
//                    }
//
//                    if (courseTeacher != null) {
//                        Course course = new Course(courseName, courseId);
//                        course.setTeacher(courseTeacher);
//                        university.addCourse(course);
//                    } else {
//                        System.out.println("Teacher with given ID not found.");
//                    }
//                    break;
//
//                case 4:
//                    List<Student> students = university.getAllStudents();
//                    System.out.println("\n--- List of Students ---");
//                    for (Student s : students) {
//                        System.out.println("Name: " + s.getName() +
//                                ", ID: " + s.getId() +
//                                ", Attendance: " + s.getAttendance());
//                    }
//                    break;
//
//                case 5:
//                    List<Course> courses = university.getAllCourses();
//                    System.out.println("\n--- List of Courses ---");
//                    for (Course c : courses) {
//                        System.out.println("Name: " + c.getName() +
//                                ", ID: " + c.getId() +
//                                (c.getTeacher() != null
//                                        ? ", Teacher: " + c.getTeacher().getName()
//                                        : ", No teacher assigned"));
//                    }
//                    break;
//
//                case 6:
//                    System.out.print("Enter student ID to update attendance: ");
//                    String updateId = scanner.nextLine();
//                    System.out.print("Enter new attendance percentage: ");
//                    double attendance = scanner.nextDouble();
//                    scanner.nextLine();
//
//                    university.updateStudentAttendance(updateId, attendance);
//                    break;
//
//                case 7:
//                    System.out.print("Enter student ID to delete: ");
//                    String deleteId = scanner.nextLine();
//                    university.deleteStudent(deleteId);
//                    break;
//
//                case 8:
//                    System.out.println("Exiting...");
//                    scanner.close();
//                    return;
//
//                default:
//                    System.out.println("Invalid option. Please try again.");
//            }
//        }
//    }
//}