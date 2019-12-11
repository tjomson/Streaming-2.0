package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VideoInfo extends Application {

    public void lmao(Video video) throws FileNotFoundException {
        HBox  imageInfoBox = new HBox();
        VBox infoBox = new VBox();

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
            yearLabel = new Label(video.getYear() + "-" + endYearString);
        } else {
            yearLabel = new Label("" + video.getYear());
        }


        HBox genreField = new HBox();
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        infoBox.getChildren().addAll(new Label(video.getTitle()),yearLabel,genreField,new Label(video.getRating() + ""));


        imageInfoBox.getChildren().addAll(imageView,infoBox);

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override


    public void start(Stage stage) throws IOException {
        String[] xd = {"lmao","bruhhh","xddddddddd"};
        Video video = new Movie("Fargo (the movie)", 1337,xd,2.3);


        HBox  imageInfoBox = new HBox();
        VBox infoBox = new VBox();

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
            yearLabel = new Label(video.getYear() + "-" + endYearString);
        } else {
            yearLabel = new Label("" + video.getYear());
        }


        HBox genreField = new HBox();
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        infoBox.getChildren().addAll(new Label(video.getTitle()),yearLabel,genreField,new Label(video.getRating() + ""));


        imageInfoBox.getChildren().addAll(imageView,infoBox);

        
        stage.setTitle("Goat");
        stage.setScene(new Scene(imageInfoBox, 400, 200));
        stage.show();
    }
}
