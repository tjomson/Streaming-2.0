package project;


import javafx.scene.image.Image;
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
    private ArrayList<Video>[] myLists = new ArrayList[]{new ArrayList<Video>(), new ArrayList<Video>(),new ArrayList<Video>(), new ArrayList<Video>()};

    private int currentUserNumber;
    private Stage currentStage;
    private Stage mainStage;

    private String[] usernames = {"","Ny Bruger","Ny Bruger", "Ny Bruger"};

    //Singleton, der kan kun være en model.
    private Model() {
    }

    public String getUsername(int userNumber){
     return usernames[userNumber];
    }

    public void setCurrentUserNumber(int userNumber){
        currentUserNumber = userNumber;

    }

    public int getCurrentUserNumber(){
        return currentUserNumber;
    }

    public void setUsername(int usernumber, String newUsername){
        usernames[usernumber] = newUsername;
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public Image getIcon(){
        return new Image(this.getClass().getClassLoader().getResourceAsStream("AndreFiler/blackSquare.png"));
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

    public String getCurrentUsername() {
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


    public List<Video> getMyList(int userNumber){
        return myLists[userNumber];
    }
    public void addToMyList(Video video,int userNumber){
        myLists[userNumber].add(video);
    }

    //Fjerner fra liste ved at sammenligne titler, da det ikke virkede at sammenligne objekter.
    public void removeFromMyList(Video video, int userNumber){
        ArrayList<Video> temporaryList = new ArrayList<>();
        for(Video v : getMyList(userNumber)){
            if(!v.getTitle().equals(video.getTitle())){
                temporaryList.add(v);
            }
        }
        myLists[userNumber] = temporaryList;
    }
}

