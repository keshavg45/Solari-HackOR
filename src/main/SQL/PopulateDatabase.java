package SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDatabase {
    public static void main (String []args){
        Connection myConnection = DBConnection.connect();
        try {
            PopulateRecruiter(myConnection, "James Johnson", "Miami Heat");
        } catch (SQLException e){
            System.out.print(e);
        }
    }

    public static void PopulateRecruiter(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "INSERT INTO Recruiter (recruiterName, companyName) VALUES ('" + arg1 + "', '" + arg2 + "')";
        ///System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Recruiter Table has been populated");
    }

    public static void InsertVolunteer(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "INSERT INTO Recruiter (recruiterName, companyName) VALUES ('" + arg1 + "', '" + arg2 + "')";
        ///System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Recruiter Table has been populated");
    }
}
