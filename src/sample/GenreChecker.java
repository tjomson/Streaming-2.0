package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenreChecker {
    public List<String> getGenreList() throws IOException {
        List<Video> movies = new MovieReader().readMovies("film.txt");
        List<Video> series = new SeriesReader().readSeries("serier.txt");
        List<Video> videos = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        videos.addAll(movies);
        videos.addAll(series);

        for(Video video :videos) {
            for(String genre : video.getGenres()){
                if(!genres.contains(genre)){
                    genres.add(genre);
                }
            }
        }
        return genres;
    }
}
