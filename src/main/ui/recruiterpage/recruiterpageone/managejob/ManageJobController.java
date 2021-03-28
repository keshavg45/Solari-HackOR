package ui.recruiterpage.recruiterpageone.managejob;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.recruiterpage.recruiterpageone.RecruiterPageOneController;

import java.io.IOException;

public class ManageJobController {

    public Button back;
    public Label companyLabel;

    public void buttonClick(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            backButtonClicked(event);
        }
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
}
