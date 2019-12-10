package sample;

public class Video {
    protected String title;
    protected int year;
    protected String[] genres;
    protected double rating;

    public Video(String title, int year, String[] genres, double rating){
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.rating = rating;

    }

    public void show(){
        System.out.print(title + " " + year + " ");
    }

    public int getYear(){
        return year;
    }

    public String getTitle(){
        return title;
    }

    public double getRating(){
        return rating;
    }
    public String[] getGenres(){
        return genres;
    }
}
