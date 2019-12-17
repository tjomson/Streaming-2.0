package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

//Denne klasse er ansvarlig for når man skal vælge bruger.
public class ChooseUserScreen {
    private Model model;
    private Stage currentStage;
    private Button user1Button;
    private Button user2Button;
    private Button user3Button;
    private Button[] userButtons;

    public void chooseUser() throws IOException {
        model = Model.getInstance();
        model.getCurrentStage().close();
        Stage stage = new Stage();

        model.addCurrentStage(stage);

        VBox window = new VBox();
        //De tre knapper til de tre brugere.
        user1Button = new Button();
        user2Button = new Button();
        user3Button = new Button();
        userButtons = new Button[]{new Button(),user1Button, user2Button, user3Button}; //new Button() tilføjes for at fylde index 0 ud.

        for(int i = 1; i<=3; i++) {
            userButtons[i].setText(new Reader().findFile("user" + i).get(0));
        }

        user1Button.setPrefWidth(100);
        user2Button.setPrefWidth(100);
        user3Button.setPrefWidth(100);

        window.setAlignment(Pos.CENTER);
        BackgroundFill background_fill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
        window.setBackground(new Background(background_fill));

        window.setSpacing(10);

        Label goatLabel = new Label("GOAT");

        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        goatLabel.setBorder(border);
        Label chooseUserLabel = new Label("Vælg bruger:");

        window.getChildren().addAll(goatLabel,chooseUserLabel,userButtons[1],userButtons[2],userButtons[3]);

        //Knappernes funktioner defineres.
        userButtons[1].setOnAction(actionEvent -> {
            try {
                userClick(1);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (loggedInAsGuestException e) {
                e.printStackTrace();
            }
        });

        userButtons[2].setOnAction(actionEvent -> {
            try {
                userClick(2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (loggedInAsGuestException e) {
                e.printStackTrace();
            }
        });
        userButtons[3].setOnAction(actionEvent -> {
            try {
                userClick(3);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (loggedInAsGuestException e) {
                e.printStackTrace();
            }
        });

        currentStage = new Stage();
        currentStage.getIcons().add(new Image("/blackSquare.png"));
        model.addCurrentStage(currentStage);
        currentStage.setScene(new Scene(window, 400, 400));
        currentStage.setTitle("GOAT");
        currentStage.show();

    }

    //Metode som opretter Min Liste når man har valgt en bruger. Hvis der oprettes en ny bruger, åbnes en dialogboks hvor man kan skrive navn.
    public void userClick(int userNumber) throws IOException, loggedInAsGuestException {

        model = Model.getInstance();
        model.addUserID(userNumber);
        model.createMyList();

        //Hvis der allerede findes en bruger.
        if (!userButtons[userNumber].getText().equals("Ny Bruger")) {
            model.addUserName(userButtons[userNumber].getText());
            new VideoSelection().openStartScene();
            model.getCurrentStage().close();
        } else {
            //Hvis der oprettes en ny bruger.
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Lav ny bruger");
            dialog.setHeaderText("GOAT UP YOUR LIFE");
            dialog.setContentText("Skriv dit navn: ");
            dialog.setGraphic(null);

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("blackSquare.png"));

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                model.addUserName(result.get());

                if (model.getUserID() == 1) {
                    userButtons[1].setText(result.get());
                    writeNewUser(1);
                }
                if (model.getUserID() == 2) {
                    userButtons[2].setText(result.get());
                    writeNewUser(2);
                }
                if (model.getUserID() == 3) {
                    userButtons[3].setText(result.get());
                    writeNewUser(3);
                }
            }
        }
    }

    public void writeNewUser(int userNumber){
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("user" + userNumber))) {
            bw.write(userButtons[userNumber].getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
