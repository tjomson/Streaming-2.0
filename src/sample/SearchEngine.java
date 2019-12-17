package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Denne klasse bruges til at finde de videoer der skal vises ud fra de givne kriterier.
public class SearchEngine {
    Model model;

    public List<Video> getSearchItems(String searchText, String sortText, String genreText, boolean showMovies, boolean showSeries,boolean onMyList) throws IOException, noSuchVideoException, loggedInAsGuestException {

        model = Model.getInstance();

        List<Movie> movies = model.getMovies();
        List<Series> series = model.getSeries();
        List<Video> videos = new ArrayList<>();

        //Hvis film er checked, skal de vises, så de tilføjes til arraylisten.
        if(showMovies) {
            videos.addAll(movies);
        }
        //Hvis serier skal vises.
        if(showSeries) {
            videos.addAll(series);
        }
        //En ny arrayliste laves, hvor videoen tilføjes hvis den har den givne genre, eller hvis genren er "Alle".
        List<Video> genreList = new ArrayList<>();
        for (Video video : videos){
            for(String string : video.getGenres()){
                if(string.equals(genreText) || genreText.equals("Alle")){
                    genreList.add(video);
                }
            }
        }

        //Hver video tjekkes om den indeholder det givne søgeord.
        List<Video> searchList = new ArrayList<>();
        for(Video video : videos){
            if(video.getTitle().toLowerCase().contains(searchText.toLowerCase())){
                searchList.add(video);
            }
        }

        //Der beholdes de elementer som både har den rette genre og det rette søgeord.
        searchList.retainAll(genreList);

        List<Video> finalList = new ArrayList<>();

        //Der tjekkes hvilke film som er på My List.
        if(onMyList) {
            if (model.getUserID()==0) {
                throw new loggedInAsGuestException("Denne funktion er ikke tilgengælig når du er logget ind som gæst. Du kan logge ind som bruger, ved at klikke på 'Skift bruger' oppe i højre hjørne.");
            }
            for(Video v : searchList){
                for(Video myListv : model.getMyList()){
                    if(v.getTitle().equals(myListv.getTitle())){
                        finalList.add(v);
                    }
                }
            }
            //Der beholdes de videoer som opfylder kriterierne.
            searchList.retainAll(finalList);
        }

        //Der tjekkes hvordan der skal sorteres, og listen ordnes ud fra det.
        if (sortText.equals("Titel: A-Å")) {
            searchList.sort(Comparator.comparing(Video::getTitle));
        }
        if (sortText.equals("Titel: Å-A")) {
            searchList.sort(Comparator.comparing(Video::getTitle).reversed());
        }
        if (sortText.equals("Årstal: Ny-Gammel")) {
            searchList.sort(Comparator.comparing(Video::getYear).reversed());
        }
        if (sortText.equals("Årstal: Gammel-Ny")) {
            searchList.sort(Comparator.comparing(Video::getYear));
        }
        if (sortText.equals("Vurdering: Bedst-Dårligst")) {
            searchList.sort(Comparator.comparing(Video::getRating).reversed());
        }
        if (sortText.equals("Vurdering: Dårligst-Bedst")) {
            searchList.sort(Comparator.comparing(Video::getRating));
        }

        if (searchList.isEmpty()) {
            throw new noSuchVideoException("Vi kunne ikke finde en video der matchede dine kriterier, prøv igen.");
        }

        return searchList;
    }
}
