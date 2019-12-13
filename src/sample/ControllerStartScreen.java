package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerStartScreen {

    @FXML
    Button guestButton;
    @FXML
    Button goToChooseUserButton;

    Model model;

    public void goToVidSelection() throws IOException, noSuchVideoException {
        model = Model.getInstance();
        model.addUserName("guest");
        model.getCurrentStage().close();

        ControllerVidSelection c = new ControllerVidSelection();
        c.openStartScene();
    }

    public void goToChooseUser() throws IOException {

        model = Model.getInstance();
        model.getCurrentStage().close();
        Stage stage = new Stage();

        model.addCurrentStage(stage);

        Parent root = FXMLLoader.load(getClass().getResource("GOAT.fxml"));
        stage.setTitle("Goat");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();

    }

}
