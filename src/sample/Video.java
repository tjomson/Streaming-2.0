package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Denne klasse er superklasse for Movie og Series.

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
    //En videos informationer bruges til at lave en VBox som kan vises.
    @Override
    public VBox toVBox() throws FileNotFoundException {

        //Videoens billede findes.
        FileInputStream f;
        if (this instanceof Movie) {
            f = new FileInputStream("Billeder/" + this.getTitle() + ".jpg");
        } else {
            f = new FileInputStream("Serier - billeder/" + this.getTitle() + ".jpg");
        }
        Image image = new Image(f);

        VBox vBox = new VBox();
        vBox.setPrefWidth(140);
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label titleLabel = new Label(this.getTitle());
        titleLabel.setWrapText(true);
        Label yearLabel;

        //Hvis det er en serie skal slutåret også vises.
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

        //Alle videoens genrer indsættes.
        FlowPane genreField = new FlowPane();
        for (String genre : this.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        //Alle informationerne tilføjes.
        Label ratingLabel = new Label("" + this.getRating());
        vBox.getChildren().addAll(new ImageView(image),titleLabel,yearLabel,genreField,ratingLabel);

        //Når den klikkes, skal et nyt vindue åbne med den video.
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
