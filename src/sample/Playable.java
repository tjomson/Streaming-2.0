package sample;

import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;

//Interface som kan benyttes hvis yderligere funktionalitet skal tilføjes til programmet.
public interface Playable {

    VBox toVBox() throws FileNotFoundException;
}
