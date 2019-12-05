package GoatSample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List readFiles(String txtLocation) throws IOException {
        File file = new File(txtLocation);

        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> videoStrings;
        videoStrings = new ArrayList<>();

        String videoInfo;

        while ((videoInfo = (br.readLine())) != null) { //At sætte videoInfo = br.readLine er nødvendigt, ellers læses kun hver anden linje.
            videoStrings.add(videoInfo);
        }
        List<String[]> splitList = new ArrayList<>();

        for(String s : videoStrings){
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
        return Double.parseDouble(s[3].replace(",","."));
    }


}


