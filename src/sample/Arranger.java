package sample;

import java.util.Comparator;
import java.util.List;

public class Arranger {

    public List<Video> arrange(List<Video> videolist, String kind) {

        if (kind.equals("Title")) {
            videolist.sort(Comparator.comparing(Video::getTitle));
        }
        if (kind.equals("Year")) {
            videolist.sort(Comparator.comparing(Video::getYear));
        }
        if (kind.equals("Rating")) {
            videolist.sort(Comparator.comparing(Video::getRating));
        }
        return videolist;
    }
}
