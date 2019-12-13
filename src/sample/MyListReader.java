package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyListReader extends Reader {

    public List<Movie> myListMovies(String myListLocation, String moviesLocation) throws IOException {

        List<String> strings = super.findFile(myListLocation);
        VideoReader mr = new MovieReader();
        List<Movie> myListMovies = new ArrayList<>();
        List<Movie> movieList = ((MovieReader) mr).readMovies(moviesLocation);

        for(String string : strings){
            for(Movie m : movieList){
                if(string.equals(m.title)){
                  myListMovies.add(m);
                }
            }
        }
        return myListMovies;

    }


    public List<Series> myListSeries(String myListLocation, String seriesLocation) throws IOException {

        List<String> strings = super.findFile(myListLocation);
        VideoReader sr = new SeriesReader();
        List<Series> myListSeries = new ArrayList<>();
        List<Series> seriesList = ((SeriesReader) sr).readSeries(seriesLocation);
        for (String string : strings) {
            for (Series s : seriesList) {
                if (string.equals(s.title)) {
                    myListSeries.add(s);
                }
            }
        }
        return myListSeries;
    }

}
