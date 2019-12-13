package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


public class ControllerChooseUser {

    @FXML
    Button user1Button;
    @FXML
    Button user2Button;
    @FXML
    Button user3Button;

    Model model;


    public void userClick1() throws IOException, noSuchVideoException {

        model = Model.getInstance();
        model.addUserNumber(1);

        if (!user1Button.getText().equals("New User")) {
            openStartSceneMethod();
            model.getCurrentStage().close();
        } else {
            openChangeUserWindow();
        }
    }

    public void userClick2() throws IOException, noSuchVideoException {

        model = Model.getInstance();
        model.addUserNumber(2);

        if (!user2Button.getText().equals("New User")) {
            openStartSceneMethod();
            model.getCurrentStage().close();
        } else {
            openChangeUserWindow();
        }
    }

    public void userClick3() throws IOException, noSuchVideoException {

        model = Model.getInstance();
        model.addUserNumber(3);

        if (!user3Button.getText().equals("New User")) {
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

    public void writeNewUser1() {
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("user1.txt"))) {
            bw.write(user1Button.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNewUser2(){
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("user2.txt"))) {
            bw.write(user2Button.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNewUser3(){
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("user3.txt"))) {
            bw.write(user3Button.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openChangeUserWindow() throws IOException {

        model = Model.getInstance();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create new user");
        dialog.setHeaderText("GOAT UP YOUR LIFE");
        dialog.setContentText("Enter your name");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (model.getUserNumber() == 1) {
                model.addUserName(result.get());
                user1Button.setText(result.get());
                writeNewUser1();
            }
            if (model.getUserNumber() == 2) {
                model.addUserName(result.get());
                user2Button.setText(result.get());
                writeNewUser2();
            }
            if (model.getUserNumber() == 3) {
                model.addUserName(result.get());
                user3Button.setText(result.get());
                writeNewUser3();
            }
        }
    }
}
