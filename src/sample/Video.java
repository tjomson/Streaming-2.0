package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Video implements Playable {

    private String title;
    private int year;
    private String[] genres;
    private double rating;

    public Video(String title, int year, String[] genres, double rating){
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.rating = rating;

    }
    @Override
    public VBox toVBox() throws FileNotFoundException {

        FileInputStream f;
        if (this instanceof Movie) {
            f = new FileInputStream("Billeder/" + this.getTitle() + ".jpg");
        } else {
            f = new FileInputStream("Serier - billeder/" + this.getTitle() + ".jpg");
        }
        Image image = new Image(f);

        VBox vBox = new VBox();
        vBox.setPrefWidth(140);
        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        vBox.setBorder(border);

        vBox.getChildren().add(new ImageView(image));
        Label titleLabel = new Label(this.getTitle());
        titleLabel.setWrapText(true);
        vBox.getChildren().add(titleLabel);
        Label yearLabel;

        if (this instanceof Series) {
            Series series1 = (Series) this;
            String endYearString;
            if (series1.getEndYear() == 0) {
                endYearString = "";
            } else {
                endYearString = series1.getEndYear() + "";
            }
            yearLabel = new Label(this.getYear() + "-" + endYearString);
        } else {
            yearLabel = new Label("" + this.getYear());
        }
        vBox.getChildren().add(yearLabel);

        FlowPane genreField = new FlowPane();

        for (String genre : this.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        Label ratingLabel = new Label("" + this.getRating());
        vBox.getChildren().addAll(genreField,ratingLabel);

        vBox.setOnMouseClicked(actionEvent ->{
            try {
                new VideoInfo().openVideoInfoScene(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return vBox;
    }

    public int getYear(){
        return year;
    }

    public String getTitle(){
        return title;
    }

    public double getRating(){
        return rating;
    }
    public String[] getGenres(){
        return genres;
    }
}
