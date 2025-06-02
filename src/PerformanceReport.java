class PerformanceReport {
    private Student student;
    private Course course;
    private double attendancePercentage;
    public double predictedGrade;

    public PerformanceReport(Student student, Course course, double attendancePercentage) {
        this.student = student;
        this.course = course;
        this.attendancePercentage = attendancePercentage;
        this.predictedGrade = predictGrade();
    }

    private double predictGrade() {
        double baseGrade = student.getGpa();
        if (attendancePercentage < 75) {
            baseGrade -= 0.5;
        } else if (attendancePercentage > 90) {
            baseGrade += 0.5;
        }
        return Math.min(4.0, Math.max(0.0, baseGrade));
    }

    public String generateReport() {
        String warning = attendancePercentage < 75 ? "WARNING: Attendance below 75%!" : "";
        return String.format("Student: %s, Course: %s, Attendance: %.2f%%, Predicted Grade: %.2f %s",
                student.getName(), course.getCourseName(), attendancePercentage, predictedGrade, warning);
    }
}