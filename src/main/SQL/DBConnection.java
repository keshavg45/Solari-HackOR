package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:/Users/yajasmalhotra/Documents/UBC/Year 2/CPSC 210/" +
                    "Projects/HackOR-Project/hackor.db"); // connect to database
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e+"");
        }

        return con;
    }
}
