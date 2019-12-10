package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerVidSelection {
    @FXML
    Button movie;
    @FXML
    Button series;
    @FXML
    Button myList;
    @FXML
    TextArea videoer;
    @FXML
    ImageView videoImages;

    Model model;

    TextField searchField = new TextField();
    ComboBox<String> sortingOptions = new ComboBox<>();
    ComboBox<String> genreOptions = new ComboBox<>();
    CheckBox movieCheckBox = new CheckBox();
    CheckBox seriesCheckBox = new CheckBox();
    Button applyButton = new Button("Apply");

    public void openStartScene() throws IOException {

        model = Model.getInstance();

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        /*
        List<Video> movies = new MovieReader().readMovies("film.txt");
        List<Video> series = new SeriesReader().readSeries("serier.txt");

        List<Video> videos = new ArrayList<>();
        videos.addAll(movies);
        videos.addAll(series);
         */
        List<Video> videos = new SearchEngine().getSearchItems("","Year","All",true,true);

        for (Video video : videos) {
            VBox vBox = new VideoVBox().getVideoVBox(video);
            flowPane.getChildren().add(vBox);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        VBox window = new VBox();

        HBox topBar = new HBox();

        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    flowPane.getChildren().clear();
                    List<Video> searchedVideos = new SearchEngine().getSearchItems(searchField.getText(), sortingOptions.getValue(), genreOptions.getValue(), movieCheckBox.isSelected(), seriesCheckBox.isSelected());
                    for(Video v : searchedVideos) {
                        VBox vBox = new VideoVBox().getVideoVBox(v);
                        flowPane.getChildren().add(vBox);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                applyButton);

        window.getChildren().addAll(topBar,scrollPane);
        window.setSpacing(10);

        Stage stage = new Stage();

        stage.setScene(new Scene(window, 1000, 800));
        stage.show();
    }

    public void openSeries() throws IOException {
        model = Model.getInstance();

        List<Series> series = model.getSeries();

        for(Series s : series) {
            s.show();
        }
    }
}