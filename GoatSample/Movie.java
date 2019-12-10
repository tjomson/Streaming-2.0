package GoatSample;

public class Movie extends Video {


    public Movie(String title, int year, String[] genres, double rating) {
        super(title, year, genres, rating);
    }

    @Override
    public void show() {
        super.show();
        for(String s : genres){
            System.out.print(s + " ");
        }
        System.out.print(rating);
        System.out.println();
    }
}