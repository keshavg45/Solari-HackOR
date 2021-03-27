package ui.volunteerpage.volunteerpageone;

import SQL.DBConnection;
import SQL.PopulateDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class VolunteerPageOneController {

    public ChoiceBox searchByRegion = new ChoiceBox();
    public ChoiceBox searchByCompany = new ChoiceBox();
    public ChoiceBox searchByJobTitle = new ChoiceBox();
    public ChoiceBox searchByTag = new ChoiceBox();
    public Button search;

    public TextField name;
    public TextField age;
    public TextField describe;
    public Button requested;
    public Button assigned;

    Connection myConnection = DBConnection.connect();
    public void buttonClick(ActionEvent event) throws IOException {
        if (event.getSource() == requested) {
//            System.out.println("list of requested jobs");
            //The only input here should be the volunteer's ID, need to figure out how to pass this from volunteerpage
            try {
                PopulateDatabase.VolunteerViewRequests(myConnection, "1");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (event.getSource() == assigned) {
//            System.out.println("list of assigned jobs");
            try {
                PopulateDatabase.VolunteerViewAssigned(myConnection,"2");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
