package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    public List<Video> getGenreVideos(List<Video> videos, String genre){
        List<Video> genreList = new ArrayList<>();
        for (Video video : videos){
            for(String string : video.getGenres()){
                if(string.equals(genre) || genre.equals("All")){
                    genreList.add(video);
                }
            }
        }

        return genreList;
    }

    public List<Video> getSearchWordVideos(List<Video> videos, String searchWord){
        List<Video> searchWordList = new ArrayList<>();
        for(Video video : videos){
            if(video.getTitle().toLowerCase().contains(searchWord.toLowerCase())){
                searchWordList.add(video);
            }
        }
        return searchWordList;
    }

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
        List<Video> genreList = getGenreVideos(videos,genreText);
        List<Video> searchList = getSearchWordVideos(videos, searchText);

        searchList.retainAll(genreList);

        new Arranger().arrange(searchList,sortText);

        return searchList;
    }
}
