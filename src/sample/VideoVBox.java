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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class VideoVBox {

    public VBox getVideoVBox(Video video) throws FileNotFoundException {

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

        FlowPane genreField = new FlowPane();

        vBox.getChildren().add(yearLabel);
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        Label ratingLabel = new Label("" + video.getRating());
        vBox.getChildren().addAll(genreField,ratingLabel);

        return vBox;
    }
}