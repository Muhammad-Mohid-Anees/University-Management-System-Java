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

class Fee {
    private int feeId;
    private int studentId;
    private Double amount;
    private LocalDate dueDate;
    private Boolean paid;
    private Double lateFine;

    public Fee(int feeId, int studentId, double amount, LocalDate dueDate) {
        this.feeId = feeId;
        this.studentId = studentId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = false;
        this.lateFine = 0.0;
    }

    public void calculateLateFine(LocalDate currentDate) {
        if (!paid && currentDate.isAfter(dueDate)) {
            long daysLate = currentDate.toEpochDay() - dueDate.toEpochDay();
            lateFine = daysLate * 50.0;
        }
    }

    public void markAsPaid() { paid = true; }
    public int getFeeId() { return feeId; }
    public int getStudentId() { return studentId; }
    public Double getAmount() { return amount; }
    public Double getLateFine() { return lateFine; }
    public Boolean isPaid() { return paid; }
    public LocalDate getDueDate() { return dueDate; }
}