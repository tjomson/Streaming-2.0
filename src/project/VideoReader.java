package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Denne klasse opdeler en string ved hvert semikolon, og returnerer en arrayliste af String-arrays.

public class VideoReader extends Reader {

    public List readFiles(String txtLocation) throws IOException {

        List<String[]> splitList = new ArrayList<>();

        for(String s : super.findFile(txtLocation)){
            splitList.add(s.split(";"));
        }
        return splitList;
    }

    public String getTitle(String[] s){
        return s[0];
    }
    public String[] getGenres(String [] s){
        return s[2].replace(" ","").split(",");
    }
    public double getRating(String[] s){
        return Double.parseDouble(s[3].replace(",", "."));
    }
}


