package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Denne klasse aflæser informationerne for en serie.
public class SeriesReader extends VideoReader {

    public List readSeries(String txtLocation) throws IOException {
        List<Video> seriesList = new ArrayList<>();

        List<String[]> series = super.readFiles(txtLocation);

        for(String[] s : series) {
            int year;
            int endYear = 0; //endYear sættes som udgangspunkt til 0, så det kan fjernes senere hvis der ikke er noget endYear.
            String[] years = s[1].split("-");
            year = Integer.parseInt(years[0].replace(" ",""));
            if(years.length == 2 && !years[1].equals(" ")) {
                endYear = Integer.parseInt(years[1]);
            }

            //Hashmap hvor key'en svarer til sæsonnummeret og entry'en svarer til antal afsnit i den sæson.
            Map<Integer,Integer> seasons = new HashMap<>();
            String[] seasonsArray = s[4].replace(" ","").split(",");
            for(String a : seasonsArray) {
                String[] seasonsEpisodes = a.split("-");
                int seasonNumber = Integer.parseInt(seasonsEpisodes[0]);
                int episodeNumber = Integer.parseInt(seasonsEpisodes[1]);
                seasons.put(seasonNumber,episodeNumber);
            }
            //Series-objekt oprettes ud fra informationerne og tilføjes til arraylisten.
            Video v = new Series(super.getTitle(s), year, super.getGenres(s), super.getRating(s), endYear, seasons);
            seriesList.add(v);
        }
        return seriesList;
    }
}

