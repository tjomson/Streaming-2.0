package sample;


import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Denne klasse benyttes til at holde oplysninger som flere andre klasser skal kunne tilgå.
public class Model {

    private List<Video> myList;
    private static Model model;
    private int chosenUser;
    private String userName;

    private Stage currentStage;
    private Stage mainStage;

    //Singleton, der kan kun være en model.
    private Model() {
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public List<Movie> getMovies() throws IOException {
        MovieReader mr = new MovieReader();
        return mr.readMovies("film.txt");
    }

    public List<Series> getSeries() throws IOException {
        SeriesReader sr = new SeriesReader();
        return sr.readSeries("serier.txt");
    }

    public void addUserID(int chosenUser) {
        this.chosenUser = chosenUser;
    }

    public void addUserName(String name) {
        userName = name;
    }

    public int getUserID() {
        return chosenUser;
    }

    public String getUserName() {
        return userName;
    }

    public void addCurrentStage(Stage stage) {
        currentStage = stage;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void addMainStage(Stage stage) {
        mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }


    public void createMyList(){
        myList = new ArrayList<>();
    }
    public List<Video> getMyList(){
        return myList;
    }
    public void addToMyList(Video video){
        myList.add(video);
    }

    //Fjerner fra liste ved at sammenligne titler, da det ikke virkede at sammenligne objekter.
    public void removeFromMyList(Video video){

        List<Video> temporaryList = new ArrayList<>();
        for(Video v : getMyList()){
            if(!v.getTitle().equals(video.getTitle())){
                temporaryList.add(v);
            }
        }
        myList = temporaryList;
    }
}

