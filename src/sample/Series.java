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

    @Override
    public void show() {
        super.show();
        System.out.print(endYear + " ");
        for(String s : genres){
            System.out.print(s + " ");
        }
        System.out.print(rating + " ");
        for(int i = 1; i <= seasons.size(); i++){
            System.out.print(seasons.get(i) + " ");
        }
        System.out.println();
    }

}
