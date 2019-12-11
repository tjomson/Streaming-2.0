package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
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
            Scene scene = new Scene(root, 400, 300);
            stage.setScene(scene);

            FileInputStream f = new FileInputStream("GoatLogo.png");
            Image image = new Image(f);
            ImageView im = new ImageView(image);

            stage.show();
        }






}
