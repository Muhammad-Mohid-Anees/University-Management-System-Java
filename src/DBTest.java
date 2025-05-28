import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/university_management";
        String user = "root"; // or your MySQL username
        String password = "Mohid.1234"; // use your actual password

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
