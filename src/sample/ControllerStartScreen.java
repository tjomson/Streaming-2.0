package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerStartScreen {

    @FXML
    Button guestButton;
    @FXML
    Button goToChooseUserButton;
    @FXML
    VBox vBox;

    Model model;

    public void goToVidSelection() throws IOException, noSuchVideoException, loggedInAsGuestException {
        model = Model.getInstance();
        model.addUserName("guest");
        model.addUserID(0);
        model.getCurrentStage().close();

        ControllerVidSelection c = new ControllerVidSelection();
        c.openStartScene();
    }

    public void goToChooseUser() throws IOException {

        model = Model.getInstance();
        model.getCurrentStage().close();
        Stage stage = new Stage();

        stage.getIcons().add(new Image("/blackSquare.png"));

        model.addCurrentStage(stage);

        Parent root = FXMLLoader.load(getClass().getResource("GOAT.fxml"));
        stage.setTitle("GOAT");
        Scene scene = new Scene(root, 400,300);
        stage.setScene(scene);
        stage.show();

    }

}
