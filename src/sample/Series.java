package sample;

import java.util.Map;

public class Series extends Video{

    protected Map seasons;
    protected int endYear;

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
