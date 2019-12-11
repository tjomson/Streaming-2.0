package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VideoInfo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override


    public void start(Stage stage) throws IOException {
        String[] xd = {"lmao","bruhhh","xddddddddd"};
        Map<Integer,Integer> mappp = new HashMap<>();
        for(int i = 1; i <= 7; i++){
            mappp.put(i,i+10);
        }

        Video video = new Series("Fargo", 1337,xd,2.3,1450,mappp);


        HBox  imageInfoBox = new HBox();
        imageInfoBox.setSpacing(20);
        VBox infoBox = new VBox();
        VBox window = new VBox();
        infoBox.setSpacing(20);

        FileInputStream f;
        if (video instanceof Movie) {
            f = new FileInputStream("Billeder/" + video.getTitle() + ".jpg");
        } else {
            f = new FileInputStream("Serier - billeder/" + video.getTitle() + ".jpg");
        }
        Image image = new Image(f);
        ImageView imageView = new ImageView(image);

        Label yearLabel;
        if (video instanceof Series) {
            Series series1 = (Series) video;
            String endYearString;
            if (series1.getEndYear() == 0) {
                endYearString = "";
            } else {
                endYearString = series1.getEndYear() + "";
            }
            yearLabel = new Label("Year: " + video.getYear() + "-" + endYearString);
        } else {
            yearLabel = new Label("Year: " + video.getYear());
        }


        HBox genreField = new HBox();
        genreField.getChildren().add(new Label("Genres: "));
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        infoBox.getChildren().addAll(new Label("Title: " + video.getTitle()),yearLabel,genreField,new Label("Rating: " + video.getRating() + ""));


        imageInfoBox.getChildren().addAll(imageView,infoBox);

        window.getChildren().add(imageInfoBox);


        if(video instanceof Series){
            Series series = (Series) video;
            ScrollPane scrollPane = new ScrollPane();
            VBox seasonsBox = new VBox();
            scrollPane.setContent(seasonsBox);
            scrollPane.setFitToWidth(true);
            infoBox.getChildren().add(new Label("Number of seasons: " + series.getSeasons().size()));
            window.getChildren().add(scrollPane);


            for(Map.Entry entry : series.getSeasons().entrySet()){

                FlowPane episodesFlowPane = new FlowPane();
                int numberOfEpisodes = (int) entry.getValue();
                for(int i = 1; i <= numberOfEpisodes; i++){
                    episodesFlowPane.getChildren().add(new Button("Episode " + i));
                }
                seasonsBox.getChildren().addAll(new Label("Season " + entry.getKey()),episodesFlowPane);
            }

        }


        stage.setTitle("Goat");
        stage.setScene(new Scene(window, 500, 500));
        stage.show();
    }
}
