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

class Admin extends Person {
    private String password;

    public Admin(int id, String name, String email, String department, String password) {
        super(id, name, email, department);
        this.password = password;
    }

    public String getPassword() { return password; }
}
