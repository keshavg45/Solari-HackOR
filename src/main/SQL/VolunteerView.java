package SQL;
import java.sql.*;

public class VolunteerView {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:hackor.db"); // connect to database
            System.out.println("Connected to database");

            Statement myStatement = con.createStatement();
            String sql = "SELECT * FROM Recruiter";
            ResultSet rs = myStatement.executeQuery(sql);

            while(rs.next()){
                System.out.print("Recruiter Name: " + rs.getString(1) + " Company Name: " +
                        rs.getString(2));
            }


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e + "");
        }

        return con;
    }
}
