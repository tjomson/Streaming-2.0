package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import javafx.scene.control.ScrollPane;
import java.io.FileInputStream;
import java.util.List;


public class MoviePosterTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        MovieReader movieReader = new MovieReader();
        List<Movie> movies = movieReader.readMovies("film.txt");

        for(Movie movie : movies){
            FileInputStream f = new FileInputStream("Billeder/" + movie.title + ".jpg");
            Image image = new Image(f);
            ImageView imageView = new ImageView(image);
            flowPane.getChildren().add(imageView);

        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane,600,400);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        Application.launch(args);
    }


}
