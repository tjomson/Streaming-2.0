package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class ControllerChooseUser {

    @FXML
    Button User1Button;
    @FXML
    Button User2Button;
    @FXML
    Button User3Button;

    Stage vidSelection;

    Model model;


    public void userClick1() throws IOException, noSuchVideoException {

        model = Model.getInstance();
        model.addUser(1);

        if (!User1Button.getText().equals("New User")) {
            openStartSceneMethod();
            model.getCurrentStage().close();
        } else {
            openChangeUserWindow();

        }

    }

    public void userClick2() throws IOException, noSuchVideoException {

        model = Model.getInstance();
        model.addUser(2);

        if (!User2Button.getText().equals("New User")) {
            openStartSceneMethod();
            model.getCurrentStage().close();
        } else {
            openChangeUserWindow();

        }

    }

    public void userClick3() throws IOException, noSuchVideoException {

        model = Model.getInstance();
        model.addUser(3);

        if (!User3Button.getText().equals("New User")) {
            openStartSceneMethod();
            model.getCurrentStage().close();
        } else {
            openChangeUserWindow();

        }

    }

    public void openStartSceneMethod() throws IOException, noSuchVideoException {
        ControllerVidSelection c = new ControllerVidSelection();
        c.openStartScene();
    }

    public void openChangeUserWindow() throws IOException {

        model = Model.getInstance();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create new user");
        dialog.setHeaderText("GOAT UP YOUR LIFE");
        dialog.setContentText("Enter your name");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (model.getUser() == 1) {
                model.addUserName(result.get());
                User1Button.setText(result.get());
            }
            if (model.getUser() == 2) {
                model.addUserName(result.get());
                User2Button.setText(result.get());
            }
            if (model.getUser() == 3) {
                model.addUserName(result.get());
                User3Button.setText(result.get());

            }
        }
    }



}







