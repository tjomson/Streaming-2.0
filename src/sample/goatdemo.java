package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class goatdemo extends Application {
    Model model;

    public static void main(String[] args) {
        launch(args);
    }

    @Override


        public void start(Stage stage) throws IOException {
            model = Model.getInstance();
            model.addCurrentStage(stage);

            Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
            stage.setTitle("Goat");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        }






}
