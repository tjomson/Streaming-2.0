package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MyList {


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
