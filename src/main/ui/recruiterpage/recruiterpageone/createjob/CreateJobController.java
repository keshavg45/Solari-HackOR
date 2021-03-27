package ui.recruiterpage.recruiterpageone.createjob;

import SQL.DBConnection;
import SQL.PopulateDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateJobController {

    public TextField name;
    public TextField companyname;
    public TextField companyname1;
    public Button conti;
    public Button back;

    Connection myConnection = DBConnection.connect();

    public void buttonClick(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            backButtonClicked(event);
        } else if (event.getSource() == conti) {
            try {
                PopulateDatabase.AddJobPosting(myConnection, name.getText(), companyname.getText(), companyname1.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            moveToPageOne(event);
        }
    }

    public void backButtonClicked(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/ui/homepage/homepage.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        DBConnection.disconnect(myConnection);
    }

    public void moveToPageOne(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/ui/recruiterpage/recruiterpageone/recruiterpageone.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        DBConnection.disconnect(myConnection);
    }
}
