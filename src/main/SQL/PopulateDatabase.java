package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDatabase {
    public static void main (String []args){
        Connection myConnection = DBConnection.connect();
        try {
             PopulateRecruiter(myConnection, "James Johnson", "Miami Heat");
//            InsertVolunteer(myConnection, "Louis Hamilton", "7");
//            InsertVolunteer(myConnection, "Toto Wolff", "33");
//            AddJobPosting(myConnection, "Software Developer", "Google", "Vancouver");
//            UpdateJobPosting(myConnection, "7");
//            VolunteerSearch(myConnection, "Software Developer", "", "", "");
//            VolunteerSearch(myConnection, "", "Google", "", "");
//            VolunteerSearch(myConnection, "", "", "Vancouver", "");
//            VolunteerSearch(myConnection, "Software Developer", "Google", "", "");
//            VolunteerSearch(myConnection, "", "Google", "Vancouver", "");
//            VolunteerSearch(myConnection, "Software Developer", "", "Vancouver", "");
//            VolunteerSearch(myConnection, "Software Developer", "Google", "Vancouver", "");
//            DeleteJobPosting(myConnection, "4");
//            RecruiterSearch(myConnection, "1");
//            VolunteerRequest(myConnection,"1", "3");
//            RecruiterAssignJob(myConnection, "1", "2");
            VolunteerViewAssigned(myConnection, "2");
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

    public static void InsertVolunteer(Connection con, String arg1, String arg2, String arg3) throws SQLException{
        Statement myStatement = con.createStatement();
        int tableSize = CountTuples(con, "Volunteer");
        tableSize++;
        String sql = "INSERT INTO Volunteer (volunteerID, volunteerName, age, description) VALUES (" + tableSize + ",'" + arg1 + "',"
                + arg2 + ",'" + arg3 + "')";
        System.out.println(sql);
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Volunteer Table has been populated");
    }

    public static void AddJobPosting(Connection con, String arg1, String arg2, String arg3) throws SQLException{
        // TODO Make sure that the table name lines up with GUI entry fields

        Statement myStatement = con.createStatement();
        int tableSize = CountTuples(con, "JobPostings");
        tableSize++;
        String sql = "INSERT INTO JobPostings (jobID, jobTitle, companyName, location, statusActive) VALUES (" + tableSize + ", '" + arg1 + "', '" + arg2 + "', '" + arg3 + "', 1)";
        //System.out.println(sql);
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

    public static void DeleteJobPosting(Connection con, String arg1) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "DELETE FROM JobPostings WHERE jobID = " + arg1;
        int rs = myStatement.executeUpdate(sql);

        System.out.println("Successfully deleted a Job Posting!");
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
        while (rs.next()){
            System.out.println("Job Options:");
            System.out.println("Job Title: " + rs.getString(1) + ", Company Name: " + rs.getString(2) + ", Location: " + rs.getString(3));
        }
    }

    public static void RecruiterSearch (Connection con, String arg1) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "SELECT Volunteer.volunteerID, Volunteer.volunteerName, Volunteer.age, Volunteer.description" +
                     " FROM JobRequests, Volunteer WHERE JobRequests.volunteerID = Volunteer.volunteerID AND JobRequests.jobID = ";
        sql = sql + arg1;
        ResultSet rs = myStatement.executeQuery(sql);

        System.out.println("Voluntees Interested in Job");
        while(rs.next()){
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Age: " + rs.getInt(3));
            System.out.println("Profile: " + rs.getString(4));
            System.out.println("");
        }
    }

    public static void VolunteerRequest(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "INSERT INTO JobRequests (jobID, volunteerID) VALUES (" + arg1 + ", " + arg2+ ")";
        int rs = myStatement.executeUpdate(sql);
    }

    public static void RecruiterAssignJob(Connection con, String arg1, String arg2) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "INSERT INTO JobAssigned (jobID, volunteerID) VALUES (" + arg1 + ", " + arg2+ ")";
        int rs = myStatement.executeUpdate(sql);
    }

    public static void VolunteerViewRequests(Connection con, String arg1) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "SELECT JobPostings.jobTitle, JobPostings.companyName, jobPostings.location FROM JobRequests, JobPostings WHERE JobRequests.jobID = JobPostings.jobID AND JobRequests.volunteerID = " + arg1;
        ResultSet rs = myStatement.executeQuery(sql);
        System.out.println("Applied Jobs");
        while (rs.next()){
            System.out.println("Job Title: " + rs.getString(1));
            System.out.println("Company Name: " + rs.getString(2));
            System.out.println("Location: " + rs.getString(3));
            System.out.println("");
        }
    }

    public static void VolunteerViewAssigned(Connection con, String arg1) throws SQLException{
        Statement myStatement = con.createStatement();
        String sql = "SELECT JobPostings.jobTitle, JobPostings.companyName, jobPostings.location FROM JobAssigned, JobPostings WHERE JobAssigned.jobID = JobPostings.jobID AND JobAssigned.volunteerID = " + arg1;
        ResultSet rs = myStatement.executeQuery(sql);
        System.out.println("Assigned Jobs");
        while (rs.next()){
            System.out.println("Job Title: " + rs.getString(1));
            System.out.println("Company Name: " + rs.getString(2));
            System.out.println("Location: " + rs.getString(3));
            System.out.println("");
        }
    }

//    SELECT Volunteer.volunteerID, Volunteer.volunteerName, Volunteer.age, Volunteer.description
//    FROM JobRequests, Volunteer
//    WHERE JobRequests.volunteerID = Volunteer.volunteerID
//    AND JobRequests.jobID = 1

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
