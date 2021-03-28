package ui.recruiterpage.recruiterpageone.closedjob;

import SQL.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ui.recruiterpage.recruiterpageone.RecruiterPageOneController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClosedJobController {

    public Button back;
    public Label companyLabel;

    public ListView<String> closedJobs;
    public List<String> closedJobPostings;

    Connection myConnection = DBConnection.connect();

    @FXML
    private void initialize() throws SQLException {

    }

    public void buttonClick(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            backButtonClicked(event);
        }
    }

    public void backButtonClicked(ActionEvent event) throws IOException {
        //Parent parent = FXMLLoader.load(getClass().getResource("/ui/recruiterpage/recruiterpageone/recruiterpageone.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/recruiterpage/recruiterpageone/" +
                "recruiterpageone.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        RecruiterPageOneController recruiterPageOneController = (RecruiterPageOneController)loader.getController();
        recruiterPageOneController.setTopLabel(companyLabel.getText());
    }

    public void setTopLabel(String text) {
        companyLabel.setText(text);
    }

    public List<String> getClosedPostings() {
        List<String> closedPostings = new ArrayList<>();

        try {
            Statement myStatement = myConnection.createStatement();
            String sql = "SELECT JobPostings.jobTitle FROM JobPostings WHERE JobPostings.statusActive = 0 AND " +
                    "JobPostings.companyName = " + "'" + companyLabel.getText() + "'" + "";

            System.out.println(sql);
            ResultSet rs = myStatement.executeQuery(sql);

            while (rs.next()) {
                closedPostings.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("THIS IS CLOSED POSTINGS");
        System.out.println(closedPostings);
        return closedPostings;
    }


}
