package sample;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    CheckBox myListCheckBox = new CheckBox();
    Button changeUserButton = new Button("Change user");
    Stage mainStage;

    public void openStartScene() throws IOException, loggedInAsGuestException {

        try {

            model = Model.getInstance();

            FlowPane flowPane = new FlowPane();
            flowPane.setVgap(10);
            flowPane.setHgap(10);
            flowPane.setAlignment(Pos.CENTER);

            List<Video> videos = new SearchEngine().getSearchItems("", "Title: A-Z", "All", true, true, false);

            for (Video video : videos) {
                VBox vBox = video.getVideoVBox();
                flowPane.getChildren().add(vBox);
            }

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(flowPane);
            scrollPane.setFitToWidth(true);

            VBox window = new VBox();

            Label userNameLabel = new Label(" You are logged in as: " + model.getUserName());

            FlowPane topBar = new FlowPane();

            topBar.setVgap(10.0);
            topBar.setHgap(10.0);

            searchField.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    updateVideoSelection(flowPane);
                }
            });

            movieCheckBox.setOnAction(actionEvent -> {
                updateVideoSelection(flowPane);
            });

            seriesCheckBox.setOnAction(actionEvent -> {
                updateVideoSelection(flowPane);
            });
            myListCheckBox.setOnAction(actionEvent -> {
                updateVideoSelection(flowPane);
            });


            genreOptions.setOnAction(actionEvent -> {
                updateVideoSelection(flowPane);
            });

            sortingOptions.setOnAction(actionEvent -> {
                updateVideoSelection(flowPane);
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

            sortingOptions.getItems().addAll("Title: A-Z", "Title: Z-A", "Year: new-old", "Year: old-new", "Rating: best-worst", "Rating: worst-best");
            sortingOptions.getSelectionModel().select(0);

            List<String> genres = new ArrayList<>();

            for (Video video : videos) {
                for (String genre : video.getGenres()) {
                    if (!genres.contains(genre)) {
                        genres.add(genre);
                    }
                }
            }

            genreOptions.getItems().add("All");
            genreOptions.getItems().addAll(genres);
            genreOptions.getSelectionModel().select(0);

            movieCheckBox.setSelected(true);
            seriesCheckBox.setSelected(true);

            myListCheckBox.setSelected(false);

            topBar.getChildren().addAll(
                    new Label(" Search for title:"),
                    searchField,
                    new Label(" Sort by:"),
                    sortingOptions,
                    new Label(" Genres:"),
                    genreOptions,


                    new Label(" Show movies"),
                    movieCheckBox,
                    new Label(" Show series"),

                    seriesCheckBox,
                    new Label(" On my list"),
                    myListCheckBox,
                    changeUserButton);

            window.getChildren().addAll(userNameLabel, topBar, scrollPane);
            window.setSpacing(10);

            mainStage = new Stage();
            model.addMainStage(mainStage);
            mainStage.setScene(new Scene(window, 1200, 600));
            mainStage.show();

        } catch (noSuchVideoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateVideoSelection(FlowPane flowPane) {
        try {
            flowPane.getChildren().clear();
            List<Video> searchedVideos = new SearchEngine().getSearchItems(searchField.getText(), sortingOptions.getValue(), genreOptions.getValue(), movieCheckBox.isSelected(), seriesCheckBox.isSelected(), myListCheckBox.isSelected());
            for (Video video : searchedVideos) {
                VBox vBox = video.getVideoVBox();
                flowPane.getChildren().add(vBox);
            }


        } catch (IOException | noSuchVideoException | loggedInAsGuestException e) {
            Label exceptionLabel = new Label(e.getMessage());
            flowPane.getChildren().add(exceptionLabel);

        }

    }
}