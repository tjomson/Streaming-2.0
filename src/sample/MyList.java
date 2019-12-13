package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MyList {

    public static void main(String[] args) throws IOException, noSuchVideoException {
        MyList myList = new MyList(1);

        List<Movie> userListMovies = new MyListReader().myListMovies("MyListUser" + myList.getUserID(),"film.txt");
        List<Series> userListSeries = new MyListReader().myListSeries("MyListUser" + myList.getUserID(),"serier.txt");

        for(Movie movie : userListMovies){
            myList.addVideo(movie);
        }
        for(Series series : userListSeries){
            myList.addVideo(series);
        }

        for(Video video : myList.getMyList()){
            System.out.println(video.getTitle());
        }
    }

    private List<Video> myList;
    int userID;

    public MyList(int userID) throws IOException {
        myList = new ArrayList<>();
        this.userID = userID;
    }

    public List<Video> getMyList(){
        return myList;
    }

    public int getUserID() {
        return userID;
    }

    public void addVideo(Video video) {
            myList.add(video);
    }

    public void removeVideo(Video video) throws noSuchVideoException {
        if (myList.contains(video)) {
        myList.remove(video);
        } else {
            throw new noSuchVideoException("The video is not on your list.");
        }
    }

    public static void ListToFile(ArrayList<String> arrayList, String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename);
        for (String line : arrayList) {
            writer.println(line);
        }
        writer.close();
    }

}
