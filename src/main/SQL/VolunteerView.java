package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VolunteerView {
    public static void main (String []args){
        Connection myConnection = DBConnection.connect();
        try {
            Statement myStatement = myConnection.createStatement();
            String sql = "SELECT * FROM Volunteer";
            ResultSet rs = myStatement.executeQuery(sql);

            System.out.println("Volunteer Bios:");
            while (rs.next()){
                System.out.println("Name: " + rs.getString(1));
                System.out.println("Age: " + rs.getString(2));
            }
        } catch (SQLException e){
            System.out.println(e + "");
        }
    }
}
