package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class MoviePosterTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        List<Video> movies = new MovieReader().readMovies("film.txt");
        List<Video> series = new SeriesReader().readSeries("serier.txt");

        List<Video> videos = new ArrayList<>();
        videos.addAll(movies);
        videos.addAll(series);
        new Arranger().arrange(videos, "Year");

        for (Video video : videos) {
            FileInputStream f;
            if (video instanceof Movie) {
                f = new FileInputStream("Billeder/" + video.getTitle() + ".jpg");
            } else {
                f = new FileInputStream("Serier - billeder/" + video.getTitle() + ".jpg");
            }
            Image image = new Image(f);

            VBox vBox = new VBox();
            vBox.setPrefWidth(140);
            Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
            vBox.setBorder(border);

            vBox.getChildren().add(new ImageView(image));
            Label titleLabel = new Label(video.getTitle());
            titleLabel.setWrapText(true);
            vBox.getChildren().add(titleLabel);
            Label yearLabel;

            if(video instanceof Series){
                Series series1 = (Series) video;
                String endYearString;
                if(series1.getEndYear() == 0){
                    endYearString = "";
                }
                else{
                    endYearString = series1.getEndYear() + "";
                }
                yearLabel = new Label(video.getYear() + "-" + endYearString);
            }
            else {
                yearLabel = new Label("" + video.getYear());
            }

            vBox.getChildren().add(yearLabel);

            FlowPane genreField = new FlowPane();

            for (String genre : video.getGenres()) {
                genreField.getChildren().add(new Label(genre + " "));
            }

            Label ratingLabel = new Label("" + video.getRating());
            vBox.getChildren().addAll(genreField,ratingLabel);
            flowPane.getChildren().add(vBox);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        VBox window = new VBox();
        TextField searchField = new TextField();
        HBox topBar = new HBox();
        Button searchButton = new Button("Search");

        ComboBox<String> sortingOptions = new ComboBox<>();
        sortingOptions.getItems().addAll("Title","Year","Rating");
        sortingOptions.getSelectionModel().select(0);

        List<String> genres = new GenreChecker().getGenreList();
        ComboBox<String> genreOptions = new ComboBox<>();
        genreOptions.getItems().add("All");
        genreOptions.getItems().addAll(genres);
        genreOptions.getSelectionModel().select(0);

        CheckBox movieCheckBox = new CheckBox();
        CheckBox seriesCheckBox = new CheckBox();
        movieCheckBox.setSelected(true);
        seriesCheckBox.setSelected(true);

        topBar.getChildren().addAll(
                new Label("Search for title "),
                searchField,
                searchButton,
                new Label("   Sort by "),
                sortingOptions,
                new Label("    Genres "),
                genreOptions,
                new Label("    Show movies "),
                movieCheckBox,
                new Label("    Show series "),
                seriesCheckBox);

        window.getChildren().addAll(topBar,scrollPane);
        window.setSpacing(10);

        stage.setScene(new Scene(window, 1000, 800));
        stage.show();
    }

    public static void main (String[]args){
        Application.launch(args);
    }
}
