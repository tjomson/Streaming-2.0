package GoatSample;

import GoatSample.Movie;

import java.io.IOException;
import java.util.List;

public class Demo {


    public static void main(String[] args) throws IOException {
        Reader r1 = new MovieReader();
        Reader r2 = new SeriesReader();

        List<Movie> movies =  ((MovieReader) r1).readMovies("GoatSample/film.txt");
        List<Series> series = ((SeriesReader) r2).readSeries("GoatSample/serier.txt");

        for(Movie m : movies) {
            m.show();
        }
        for(Series s : series) {
            s.show();
        }
    }
}


