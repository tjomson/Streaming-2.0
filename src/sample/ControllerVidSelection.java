package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
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

    public void openMovies() throws IOException {

        model = Model.getInstance();

        List<Movie> movies = model.getMovies();

        for(Movie m : movies){
            m.show();
        }

        videoer.setText(model.getUserName());

}

    public void openSeries() throws IOException {
        model = Model.getInstance();

        List<Series> series = model.getSeries();

        for(Series s : series) {
            s.show();
        }
    }
}
