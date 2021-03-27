package ui.recruiterpage.recruiterpageone;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RecruiterPageOneController {

    public Button createJob;
    public Button manageJob;
    public Button closedJob;

    public void categorySelected(ActionEvent event) throws IOException {
        if (event.getSource() == createJob) {
            switchScenes(event, "createJob");
        } else if (event.getSource() == manageJob) {
            switchScenes(event, "manageJob");
        } else if (event.getSource() == closedJob) {
            switchScenes(event, "closedJob");
        }
    }

    public void switchScenes(ActionEvent event, String string) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/recruiterpage/recruiterpageone/" + string + "/" + string + ".fxml"));
        Parent courseParent = loader.load();
        Scene courseScene = new Scene(courseParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(courseScene);
        window.show();
    }
}
