package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends Application {
    private Model model;

    //Denne klasse er det første man ser når man kører programmet, og det er derfor main-metoden er her.
    @Override
    public void start(Stage stage) throws Exception {

        VBox window = new VBox();
        Image i = new Image(this.getClass().getResource("/AndreFiler/GoatLogo.png").toExternalForm());
        ImageView iv = new ImageView(i);
        iv.setFitHeight(100);
        iv.setFitWidth(300);
        window.getChildren().add(iv);

        window.setAlignment(Pos.CENTER);
        BackgroundFill background_fill = new BackgroundFill(Color.RED,
                CornerRadii.EMPTY, Insets.EMPTY);
        window.setBackground(new Background(background_fill));

        window.setSpacing(10);

        //De to knapper tilføjes og deres funktioner defineres.
        Button continueAsGuestButton = new Button("Fortsæt som gæst");
        Button signInAsUserButton = new Button("Log ind som bruger");
        window.getChildren().addAll(continueAsGuestButton,signInAsUserButton);

        continueAsGuestButton.setPrefWidth(130);
        signInAsUserButton.setPrefWidth(130);

        continueAsGuestButton.setOnAction(actionEvent -> {
            try {
                model = Model.getInstance();
                model.addUserName("gæst");
                model.addUserID(0);
                model.getCurrentStage().close();

                new VideoSelection().openStartScene();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (project.loggedInAsGuestException e) {
                e.printStackTrace();
            }
        });

        signInAsUserButton.setOnAction(actionEvent -> {
            new ChooseUserScreen().chooseUser();
        });

        model = Model.getInstance();

        stage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("AndreFiler/blackSquare.png")));

        model.addCurrentStage(stage);

        stage.setTitle("GOAT");
        Scene scene = new Scene(window, 400,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
