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
        new Arranger().arrange(videos, "Title");

        for (Video video : videos) {
            FileInputStream f;

            if (video instanceof Movie) {
                f = new FileInputStream("Billeder/" + video.title + ".jpg");
            } else {
                f = new FileInputStream("Serier - billeder/" + video.title + ".jpg");
            }
            Image image = new Image(f);

            VBox vBox = new VBox();
            vBox.setPrefWidth(140);
            Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
            vBox.setBorder(border);

            vBox.getChildren().add(new ImageView(image));
            Label titleLabel = new Label(video.title);
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
                yearLabel = new Label("" + video.year);
            }

            vBox.getChildren().add(yearLabel);

            FlowPane genreField = new FlowPane();

            for (String s : video.genres) {
                genreField.getChildren().add(new Label(s + " "));
            }

            Label ratingLabel = new Label("" + video.rating);
            vBox.getChildren().addAll(genreField,ratingLabel);
            flowPane.getChildren().add(vBox);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        VBox window = new VBox();
        TextField searchField = new TextField();
        HBox searchBar = new HBox();
        Button searchButton = new Button("Search");
        searchBar.getChildren().addAll(new Label("Search for title "),searchField,searchButton,new Label("   Sort by "));

        //Crasher af en eller anden grund når man klikker på drop down menuen.
        ComboBox<String> sortingOptions = new ComboBox<>();
        sortingOptions.getItems().addAll("Title","Year","Rating");
        sortingOptions.getSelectionModel().select(0);
        searchBar.getChildren().add(sortingOptions);

        window.getChildren().addAll(searchBar,scrollPane);
        window.setSpacing(10);

        stage.setScene(new Scene(window, 1000, 800));
        stage.show();
    }

    public static void main (String[]args){
        Application.launch(args);
    }
}
