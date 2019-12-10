package GoatSample;

import java.util.ArrayList;
import java.util.List;

public class Model {

    User user;
    List<User> users;
    List<Movie> movies;
    List<Series> series;
    private static Model model;
    int chosenUser;

    private Model() {
        users = new ArrayList<>();
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public void showMovies() {
        MovieReader mr = new MovieReader();
        mr.readMovies();
    }

    public void addUser(int chosenUser) {
        this.chosenUser = chosenUser;
    }

    public int getUser() {
        return chosenUser;
    }




}
