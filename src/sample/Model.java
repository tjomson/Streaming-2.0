package sample;


import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    User user;
    List<User> users;
    List<Movie> movies;
    List<Series> series;
    List<Video> myList;
    private static Model model;
    int chosenUser;
    String userName = "New User";

    Stage currentStage;
    Stage mainStage;
    String user1ButtonString = "New User";
    String user2ButtonString = "New User";
    String user3ButtonString = "New User";

    private Model() {
        users = new ArrayList<>();
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


    public void setButton1(String name) {
        user1ButtonString = name;
    }
    public void setButton2(String name) {
        user2ButtonString = name;
    }
    public void setButton3(String name) {
        user3ButtonString = name;
    }
    public String getUser1Button() {
        return user1ButtonString;
    }
    public String getUser2Button() {
        return user2ButtonString;
    }
    public String getUser3Button() {
        return user3ButtonString;
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

