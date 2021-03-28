package ui.recruiterpage.recruiterpageone.managejob;

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

public class ManageJobController {

    public Button back;
    public Button viewVolunteers;
    public Button closePosting;
    public Button addVolunteer;

    public Label companyLabel;

    public ListView<String> jobPostings;
    public ListView<String> volunteersAvailable;

    public int jobSelectedIndex;
    public int volunteerSelectedIndex;

    Connection myConnection = DBConnection.connect();

    @FXML
    private void initialize() {
        jobPostings.getItems().add("Job 1");
        jobPostings.getItems().add("Job 2");
        jobPostings.getItems().add("Job 3");
    }

    public void buttonClick(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            backButtonClicked(event);
        } else if (event.getSource() == viewVolunteers) {
            showVolunteers();
        } else if (event.getSource() == closePosting) {
            closeJobPosting();
        } else if (event.getSource() == addVolunteer) {
            addaVolunteer();
        }
    }

    public void showVolunteers() {
        if (jobSelectedIndex == 0) {
            volunteersAvailable.getItems().clear();
            volunteersAvailable.getItems().add("Mikey");
            volunteersAvailable.getItems().add("Dwight");
            volunteersAvailable.getItems().add("Jim");
        } else if (jobSelectedIndex == 1) {
            volunteersAvailable.getItems().clear();
            volunteersAvailable.getItems().add("Angela");
            volunteersAvailable.getItems().add("Pam");
            volunteersAvailable.getItems().add("Andy");
        }  else if (jobSelectedIndex == 2) {
            volunteersAvailable.getItems().clear();
            volunteersAvailable.getItems().add("Stanley");
            volunteersAvailable.getItems().add("Micheal");
            volunteersAvailable.getItems().add("Creed");
        }
    }

    public void closeJobPosting() {

    }

    public void addaVolunteer() {

    }

    public void selectJob() {
        jobSelectedIndex = jobPostings.getSelectionModel().getSelectedIndex();
        System.out.println("Manage Job Index:" + jobSelectedIndex);
    }

    public void selectVolunteer() {
        volunteerSelectedIndex = volunteersAvailable.getSelectionModel().getSelectedIndex();
        System.out.println("Volunteers Index:" + volunteerSelectedIndex);
    }

    public void backButtonClicked(ActionEvent event) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("/ui/recruiterpage/recruiterpageone/recruiterpageone.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/recruiterpage/recruiterpageone/" +
                "recruiterpageone.fxml"));
        Parent parent = loader.load();
        RecruiterPageOneController recruiterPageOneController = (RecruiterPageOneController)loader.getController();
        recruiterPageOneController.setTopLabel(companyLabel.getText());
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void setTopLabel(String text) {
        companyLabel.setText(text);
    }

    public void getOpenPostings() {
        List<String> openPostings = new ArrayList<>();

        try {
            Statement myStatement = myConnection.createStatement();
            String sql = "SELECT JobPostings.jobTitle FROM JobPostings WHERE JobPostings.statusActive = 1 AND " +
                    "JobPostings.companyName = " + "'" + companyLabel.getText() + "'" + "";

            System.out.println(sql);
            ResultSet rs = myStatement.executeQuery(sql);

            while (rs.next()) {
                openPostings.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(openPostings);
    }
}
