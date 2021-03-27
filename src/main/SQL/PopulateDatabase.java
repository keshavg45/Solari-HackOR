package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDatabase {
    public static void main (String []args){
        Connection myConnection = DBConnection.connect();
        try {
            //PopulateRecruiter(myConnection, "James Johnson", "Miami Heat");
            InsertVolunteer(myConnection, "Louis Hamilton", "7");
            InsertVolunteer(myConnection, "Toto Wolff", "33");
        } catch (SQLException e){
            System.out.print(e);
        }
    }

    public static void PopulateRecruiter(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        int tableSize = CountTuples(con, "Recruiter");
        tableSize++;
        String sql = "INSERT INTO Recruiter (recruiterID, recruiterName, companyName) VALUES (" + tableSize + ",'" +
                arg1 + "','" + arg2 + "')";
        System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Recruiter Table has been populated");
    }

    public static void InsertVolunteer(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        int tableSize = CountTuples(con, "Volunteer");
        tableSize++;
        String sql = "INSERT INTO Volunteer (volunteerID, volunteerName, age) VALUES (" + tableSize + ",'" + arg1 + "',"
                + arg2 + ")";
        System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Volunteer Table has been populated");
    }

    public static int CountTuples(Connection con, String arg1){
        int size = 0;
        try {
            Statement myStatement = con.createStatement();
            String sql = "SELECT COUNT (*) FROM " + arg1;
            ResultSet rs = myStatement.executeQuery(sql);
            size = rs.getInt(1);
        } catch (SQLException e){
            System.out.println("Error in retrieving table");
        }
        return size;
    }
}
