package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader extends UniversalReader{

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
        return Double.parseDouble(s[3].replace(",","."));
    }
}


