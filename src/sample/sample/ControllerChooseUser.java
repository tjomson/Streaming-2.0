package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class ControllerChooseUser {

    @FXML
    Button user1;
    @FXML
    Button user2;
    @FXML
    Button user3;

    Stage vidSelection;

    Model model;

    Stage openStart;

    public void userClick1() throws IOException {

        model = Model.getInstance();
        model.addUser(1);

        if (!user1.getText().equals("New User")) {
            openStartSceneMethod();
        } else {
            openChangeUserWindow();

        }

    }

    public void userClick2() throws IOException {

        model = Model.getInstance();
        model.addUser(2);

        if (!user2.getText().equals("New User")) {
            openStartSceneMethod();

        } else {
            openChangeUserWindow();

        }

    }

    public void userClick3() throws IOException {

        model = Model.getInstance();
        model.addUser(3);

        if (!user3.getText().equals("New User")) {
            openStartSceneMethod();

        } else {
            openChangeUserWindow();

        }

    }

    public void openStartSceneMethod() throws IOException {
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
                user1.setText(result.get());
            }
            if (model.getUser() == 2) {
                model.addUserName(result.get());
                user2.setText(result.get());
            }
            if (model.getUser() == 3) {
                model.addUserName(result.get());
                user3.setText(result.get());

            }
        }
    }


            public void openVideoSelection() throws IOException {

                model = Model.getInstance();

                vidSelection = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("videoSelection.fxml"));
                vidSelection.setTitle(model.getUserName() + " - All videos and series");
                vidSelection.setScene(new Scene(root, 400, 200));
                vidSelection.show();
            }
        }







