package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchEngine {
    private List<Video> myListVideos;
    Model model;

    public void addVideoToMyList(Video video){
        myListVideos.add(video);
    }
    public void removeVideoFromMyList(Video video){
        myListVideos.remove(video);
    }

    public static void listToFile(ArrayList<Video> arrayList, String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename);

        for (Video video : arrayList) {
            writer.println(video.getTitle());
        }
        writer.close();
    }

    public List<Video> getSearchItems(String searchText, String sortText, String genreText, boolean showMovies, boolean showSeries,boolean onMyList,int userID) throws IOException, noSuchVideoException {

        model = Model.getInstance();

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

        List<Video> finalList = new ArrayList<>();

        if(onMyList) {
            for(Video v : searchList){
                for(Video myListv : model.getMyList()){
                    if(v.getTitle().equals(myListv.getTitle())){
                        finalList.add(v);
                    }
                }
            }
            searchList.retainAll(finalList);
        }


        if (sortText.equals("Title: A-Z")) {
            searchList.sort(Comparator.comparing(Video::getTitle));
        }
        if (sortText.equals("Title: Z-A")) {
            searchList.sort(Comparator.comparing(Video::getTitle).reversed());
        }
        if (sortText.equals("Year: new-old")) {
            searchList.sort(Comparator.comparing(Video::getYear).reversed());
        }
        if (sortText.equals("Year: old-new")) {
            searchList.sort(Comparator.comparing(Video::getYear));
        }
        if (sortText.equals("Rating: best-worst")) {
            searchList.sort(Comparator.comparing(Video::getRating).reversed());
        }
        if (sortText.equals("Rating: worst-best")) {
            searchList.sort(Comparator.comparing(Video::getRating));
        }

        if (searchList.isEmpty()) {
            throw new noSuchVideoException("We couldn't find any video matching your criteria, please try again");
        }

        return searchList;
    }
}
