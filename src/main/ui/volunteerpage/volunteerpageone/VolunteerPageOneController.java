package ui.volunteerpage.volunteerpageone;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class VolunteerPageOneController {

    public ChoiceBox searchByRegion = new ChoiceBox();
    public ChoiceBox searchByCompany = new ChoiceBox();
    public ChoiceBox searchByJobTitle = new ChoiceBox();
    public ChoiceBox searchByTag = new ChoiceBox();

    public Button search;
    public Button requestedJobs;
    public Button assignedJobs;

    public void fillChoiceBoxes() {

    }

    public void buttonClick(ActionEvent event) throws IOException {
        if (event.getSource() == search) {
           //
        } else if (event.getSource() == requestedJobs) {
           //
        } else if (event.getSource() == assignedJobs) {
           //
        }
    }
}
