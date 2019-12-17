package sample;

import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public interface Playable {

    VBox toVBox() throws FileNotFoundException;
}
