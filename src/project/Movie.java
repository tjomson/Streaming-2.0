package project;

//Klasse til at oprette Movie-objekter. Tilføjer ikke noget til superklassen.
public class Movie extends Video{

    public Movie(String title, int year, String[] genres, double rating) {
        super(title, year, genres, rating);
    }
}