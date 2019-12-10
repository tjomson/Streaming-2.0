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
    private static Model model;
    int chosenUser;
    String userName;
    Stage currentStage;

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

    public void addUser(int chosenUser) {
        this.chosenUser = chosenUser;
    }

    public void addUserName(String name) {
        userName = name;
    }

    public int getUser() {
        return chosenUser;
    }

    public String getUserName() {
            return userName;
        }

        public void addCurrentStage(Stage stage) {
            currentStage = stage;
        }

        public Stage getStage() {
        return currentStage;
        }
    }





