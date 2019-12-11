package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchEngine {

    public List<Video> getSearchItems(String searchText, String sortText, String genreText, boolean showMovies, boolean showSeries) throws IOException {

        List<Video> movies = new MovieReader().readMovies("film.txt");
        List<Video> series = new SeriesReader().readSeries("serier.txt");
        List<Video> videos = new ArrayList<>();
        if(showMovies) {
            videos.addAll(movies);
        }
        if(showSeries) {
            videos.addAll(series);
        }
        List<Video> genreList = new ArrayList<>();
        for (Video video : videos){
            for(String string : video.getGenres()){
                if(string.equals(genreText) || genreText.equals("All")){
                    genreList.add(video);
                }
            }
        }

        List<Video> searchList = new ArrayList<>();
        for(Video video : videos){
            if(video.getTitle().toLowerCase().contains(searchText.toLowerCase())){
                searchList.add(video);
            }
        }

        searchList.retainAll(genreList);

        if (sortText.equals("Title")) {
            searchList.sort(Comparator.comparing(Video::getTitle));
        }
        if (sortText.equals("Year")) {
            searchList.sort(Comparator.comparing(Video::getYear).reversed());
        }
        if (sortText.equals("Rating")) {
            searchList.sort(Comparator.comparing(Video::getRating).reversed());
        }

        return searchList;
    }
}
