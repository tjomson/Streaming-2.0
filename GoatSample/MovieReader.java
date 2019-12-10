package GoatSample;

import GoatSample.Movie;
import GoatSample.Reader;
import GoatSample.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieReader extends Reader {

    public List readMovies(String txtLocation) throws IOException {
        List<Video> moviesList = new ArrayList<>();

        List<String[]> movies = super.readFiles(txtLocation);

        for(String[] s : movies) {
            int year = Integer.parseInt(s[1].replace(" ",""));
            Video v = new Movie(super.getTitle(s), year, super.getGenres(s), super.getRating(s));
            moviesList.add(v);
        }
        return moviesList;
    }
}



