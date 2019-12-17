package sample;

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

import java.io.FileInputStream;
import java.io.IOException;

public class StartScreen extends Application {
    private Model model;

    @Override
    public void start(Stage stage) throws Exception {

        VBox window = new VBox();
        FileInputStream f = new FileInputStream("GoatLogo.png");
        Image i = new Image(f);
        ImageView iv = new ImageView(i);
        iv.setFitHeight(100);
        iv.setFitWidth(300);
        window.getChildren().add(iv);

        window.setAlignment(Pos.CENTER);
        BackgroundFill background_fill = new BackgroundFill(Color.RED,
                CornerRadii.EMPTY, Insets.EMPTY);
        window.setBackground(new Background(background_fill));

        window.setSpacing(10);

        Button continueAsGuestButton = new Button("Fortsæt som gæst");
        Button signInAsUserButton = new Button("Log ind som bruger");
        window.getChildren().addAll(continueAsGuestButton,signInAsUserButton);

        continueAsGuestButton.setOnAction(actionEvent -> {
            try {
                goToVidSelection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (sample.loggedInAsGuestException e) {
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
    public void goToVidSelection() throws IOException, loggedInAsGuestException {
        model = Model.getInstance();
        model.addUserName("gæst");
        model.addUserID(0);
        model.getCurrentStage().close();

        new VideoSelection().openStartScene();
    }

    public void goToChooseUser() throws IOException {
        new ChooseUserScreen().chooseUser();

    }
}
