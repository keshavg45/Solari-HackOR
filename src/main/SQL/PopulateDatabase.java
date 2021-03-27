package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDatabase {
    public static void main (String []args){
        Connection myConnection = DBConnection.connect();
        try {
//            PopulateRecruiter(myConnection, "James Johnson", "Miami Heat");
//            InsertVolunteer(myConnection, "Louis Hamilton", "7");
//            InsertVolunteer(myConnection, "Toto Wolff", "33");
//            AddJobPosting(myConnection, "Author", "Oracle", "Vancouver");
//            UpdateJobPosting(myConnection, "7");
//            VolunteerSearch(myConnection, "Author", "", "", "");
//            VolunteerSearch(myConnection, "", "Google", "", "");
            VolunteerSearch(myConnection, "", "", "Vancouver", "");
//            VolunteerSearch(myConnection, "Software Developer", "Google", "", "");
//            VolunteerSearch(myConnection, "", "Google", "Vancouver", "");
//            VolunteerSearch(myConnection, "Software Developer", "", "Vancouver", "");
//            VolunteerSearch(myConnection, "Software Developer", "Google", "Vancouver", "");
        } catch (SQLException e){
            System.out.print(e);
        }
    }

    public static void PopulateRecruiter(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "INSERT INTO Recruiter (recruiterName, companyName) VALUES ('" + arg1 + "', '" + arg2 + "')";
        System.out.println(sql);
        //int rs = myStatement.executeUpdate(sql);

        System.out.println("Recruiter Table has been populated");
    }

    public static void InsertVolunteer(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        int tableSize = CountTuples(con, "Volunteer");
        tableSize++;
        String sql = "INSERT INTO Volunteer (volunteerID, volunteerName, age) VALUES (" + tableSize + ",'" + arg1 + "'," + arg2 + ")";
        //System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Volunteer Table has been populated");
    }

    public static void AddJobPosting(Connection con, String arg1, String arg2, String arg3) throws SQLException{
        Statement myStatement = con.createStatement();
        int tableSize = CountTuples(con, "JobPostings");
        tableSize++;
        String sql = "INSERT INTO JobPostings (jobID, jobTitle, companyName, location, statusActive) VALUES (" + tableSize + ", '" + arg1 + "', '" + arg2 + "', '" + arg3 + "', 1)";
        System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Successfully Added new Job Posting!");
    }

    public static void UpdateJobPosting(Connection con, String arg1) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql ="UPDATE JobPostings SET statusActive = 0 WHERE jobID = " + arg1;
        System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Successfully Changed the status of Job Posting!");
    }

    public static void VolunteerSearch (Connection con, String arg1, String arg2, String arg3, String arg4) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "SELECT * FROM JobPostings WHERE ";
        int currentLength = sql.length();
        if (arg1.length() != 0){
            sql = sql + "jobTitle = '" + arg1 + "'";
        }
        if (arg2.length() != 0){
            if (sql.length() != currentLength) {
                currentLength = sql.length();
                sql = sql + " AND companyName = '" + arg2 + "'";
            } else {
                sql = sql + "companyName = '" + arg2 + "'";
            }
        }
        if (arg3.length() != 0){
            if (sql.length() != currentLength) {
                currentLength = sql.length();
                sql = sql + " AND location = '" + arg3 + "'";
            } else {
                sql = sql + "location = '" + arg3 + "'";
            }
        }
        if (sql.length() != currentLength) {
            sql = sql + " AND statusActive = 1";
        } else {
            sql = sql + "statusActive = 1";
        }
        System.out.println(sql);
        ResultSet rs = myStatement.executeQuery(sql);
        System.out.println("Job Options:");
        while (rs.next()){
            System.out.println("Job Title: " + rs.getString(2) + ", Company Name: " + rs.getString(3) + ", Location: " + rs.getString(4));
        }
    }
//
//    SELECT *
//    FROM JobPostings
//    WHERE jobTitle = 'Software Developer'
//    AND companyName = 'Google'
//    AND location = 'Vancouver'
//    AND statusActive = 1

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
