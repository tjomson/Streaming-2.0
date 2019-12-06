package sample;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException {
        Reader r1 = new MovieReader();
        Reader r2 = new SeriesReader();
        MyList myList = new MyList();

        List<Movie> movies = ((MovieReader) r1).readMovies("film.txt");
        List<Series> series = ((SeriesReader) r2).readSeries("serier.txt");

        UniversalReader u = new UniversalReader();

        List<Image> movieImg = new ArrayList<>();
        List<Image> seriesImg = new ArrayList<>();

        List<String> stringList = new ArrayList<>();


        for(Movie m : movies){
            stringList.add(m.title);
        }


        myList.ListToFile((ArrayList<String>) stringList,"MyListUser1");
        List<String> uniTest = u.findFile("MyListUser1");


        MyListReader mlr = new MyListReader();


        ArrayList<Movie> movieList = (ArrayList<Movie>) mlr.MyListMovies("MyListUser1","film.txt");
        ArrayList<Series> seriesList = (ArrayList<Series>) mlr.MyListSeries("MyListUser1", "serier.txt");


        for(Movie m : movieList){
            m.show();
        }

        for(Series s : seriesList){
            s.show();
        }


/*

        for (Movie m : movies) {
            m.show();
        }
        for (Series s : series) {
            s.show();
        }

        for(Movie m : movies){
            Image image = new Image(new FileInputStream("Billeder\\" + m.title + ".jpg"));
            movieImg.add(image);
        }

        for (Series s : series) {
            Image image = new Image(new FileInputStream("Serier - billeder\\" + s.title + ".jpg"));
            seriesImg.add(image);

        }
*/


    }
}
