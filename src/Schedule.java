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

class Schedule {
    private int scheduleId;
    private Course course;
    private Room room;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule(int scheduleId, Course course, Room room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.scheduleId = scheduleId;
        this.course = course;
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean hasConflict(Schedule other) {
        return this.room.equals(other.room) && this.date.equals(other.date) &&
                !(this.endTime.isBefore(other.startTime) || this.startTime.isAfter(other.endTime));
    }

    public Course getCourse() { return course; }
    public Room getRoom() { return room; }
    public LocalDate getDate() { return date; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}