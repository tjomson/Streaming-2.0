package sample;


import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    User user;
    List<User> users;
    List<Movie> movies;
    List<Series> series;
    private static Model model;
    int chosenUser;
    String userName;

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

    public void addUserNumber(int chosenUser) {
        this.chosenUser = chosenUser;
    }

    public void addUserName(String name) {
        userName = name;
    }

    public int getUserNumber() {
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

}

