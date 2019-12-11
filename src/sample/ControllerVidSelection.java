package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerVidSelection {

    Model model;

    TextField searchField = new TextField();
    ComboBox<String> sortingOptions = new ComboBox<>();
    ComboBox<String> genreOptions = new ComboBox<>();
    CheckBox movieCheckBox = new CheckBox();
    CheckBox seriesCheckBox = new CheckBox();
    Button changeUserButton = new Button("Change user");
    Stage mainStage;
    Stage restartStage;

    public void openStartScene() throws IOException {

        model = Model.getInstance();

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        List<Video> videos = new SearchEngine().getSearchItems("","Title","All",true,true);

        for (Video video : videos) {
            VBox vBox = video.getVideoVBox();
            flowPane.getChildren().add(vBox);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        VBox window = new VBox();

        Label userNameLabel = new Label("You are logged in as: " + model.getUserName());

        HBox topBar = new HBox();

        searchField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                hygge(flowPane);
            }
        });

        movieCheckBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hygge(flowPane);
            }
        });

        seriesCheckBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hygge(flowPane);
            }
        });

        genreOptions.setOnAction(actionEvent -> {
            hygge(flowPane);
        });

        sortingOptions.setOnAction(actionEvent -> {
            hygge(flowPane);
        });

        changeUserButton.setOnAction(actionEvent -> {
            ControllerStartScreen c = new ControllerStartScreen();
            try {
                c.goToChooseUser();
                model.getMainStage().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sortingOptions.getItems().addAll("Title","Year","Rating");
        sortingOptions.getSelectionModel().select(0);

        List<String> genres = new GenreChecker().getGenreList();
        genreOptions.getItems().add("All");
        genreOptions.getItems().addAll(genres);
        genreOptions.getSelectionModel().select(0);

        movieCheckBox.setSelected(true);
        seriesCheckBox.setSelected(true);

        topBar.getChildren().addAll(
                new Label("Search for title "),
                searchField,
                new Label("   Sort by "),
                sortingOptions,
                new Label("    Genres "),
                genreOptions,
                new Label("    Show movies "),
                movieCheckBox,
                new Label("    Show series "),
                seriesCheckBox,
                changeUserButton);

        window.getChildren().addAll(userNameLabel,topBar,scrollPane);
        window.setSpacing(10);

        mainStage = new Stage();
        model.addMainStage(mainStage);
        mainStage.setScene(new Scene(window, 1000, 800));
        mainStage.show();
    }

    public void hygge(FlowPane flowPane){
        try {
            flowPane.getChildren().clear();
            List<Video> searchedVideos = new SearchEngine().getSearchItems(searchField.getText(), sortingOptions.getValue(), genreOptions.getValue(), movieCheckBox.isSelected(), seriesCheckBox.isSelected());
            for(Video video : searchedVideos) {
                VBox vBox = video.getVideoVBox();
                flowPane.getChildren().add(vBox);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}