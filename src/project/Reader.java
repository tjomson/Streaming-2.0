package project;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Denne klasse er ansvarlig for at læse en fil og returnere hver linje i en arrayliste.
public class Reader {
    public List<String> findFile(String fileName) throws IOException {

        //File file = new File(fileName);

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("AndreFiler/" + fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "ISO-8859-1")); //ISO-8859-1 gør at man kan læse specielle tegn, f.eks. ä.

        List<String> videoStrings = new ArrayList<>();

        String videoInfo;

        while ((videoInfo = (br.readLine())) != null) { //At sætte videoInfo = br.readLine er nødvendigt, ellers læses kun hver anden linje.
            videoStrings.add(videoInfo);
        }
        return videoStrings;
    }
}
