package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Denne klasse aflæser informationerne for en film.
public class MovieReader extends VideoReader {

    public List readMovies(String txtLocation) throws IOException {
        List<Video> moviesList = new ArrayList<>();

        List<String[]> movies = super.readFiles(txtLocation);

        for(String[] s : movies) {
            int year = Integer.parseInt(s[1].replace(" ",""));
            //Nyt Movie-objekt oprettes og tilføjes til listen.
            Video v = new Movie(super.getTitle(s), year, super.getGenres(s), super.getRating(s));
            moviesList.add(v);
        }
        return moviesList;
    }

}



