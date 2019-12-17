package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<String> findFile(String txtLocation) throws IOException {

        File file = new File(txtLocation);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1")); //ISO-8859-1 gør at man kan læse specielle tegn, f.eks. ä

        List<String> videoStrings = new ArrayList<>();

        String videoInfo;

        while ((videoInfo = (br.readLine())) != null) { //At sætte videoInfo = br.readLine er nødvendigt, ellers læses kun hver anden linje.
            videoStrings.add(videoInfo);
        }
        return videoStrings;
    }
}
