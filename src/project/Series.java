package project;

import java.util.Map;

//Denne klasse bruges til at oprette Series-objekter.
public class Series extends Video{

    private Map seasons;
    private int endYear;

    public Series(String title, int year, String[] genres, double rating, int endYear, Map seasons) {
        super(title, year, genres, rating);
        this.endYear = endYear;
        this.seasons = seasons;
    }


    public int getEndYear(){
        return endYear;
    }
    public Map<Integer,Integer> getSeasons(){
        return seasons;
    }
}
