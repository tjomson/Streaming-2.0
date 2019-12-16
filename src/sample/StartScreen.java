package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends Application {
    Model model;


    @Override
    public void start(Stage stage) throws Exception {


        VBox window = new VBox();
        Button continueAsGuestButton = new Button("Continue as guest");
        Button signInAsUserButton = new Button("Sign in as user");
        window.getChildren().addAll(continueAsGuestButton,signInAsUserButton);

        continueAsGuestButton.setOnAction(actionEvent -> {
            try {
                goToVidSelection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (noSuchVideoException e) {
                e.printStackTrace();
            } catch (loggedInAsGuestException e) {
                e.printStackTrace();
            }
        });

        signInAsUserButton.setOnAction(actionEvent -> {
            try {
                goToChooseUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        model = Model.getInstance();

        stage.getIcons().add(new Image("/blackSquare.png"));

        model.addCurrentStage(stage);


        stage.setTitle("GOAT");
        Scene scene = new Scene(window, 400,300);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
    public void goToVidSelection() throws IOException, noSuchVideoException, loggedInAsGuestException {
        model = Model.getInstance();
        model.addUserName("guest");
        model.addUserID(0);
        model.getCurrentStage().close();

        ControllerVidSelection c = new ControllerVidSelection();
        c.openStartScene();
    }
    public void goToChooseUser() throws IOException {

        ChooseUserScreen c = new ChooseUserScreen();
        c.chooseUser();

    }
}