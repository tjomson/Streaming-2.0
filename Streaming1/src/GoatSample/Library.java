package GoatSample;

import GoatSample.Video;

import java.util.ArrayList;

public class Library {

    private ArrayList<Video> library;

    public Library() {
        library = new ArrayList<>();
    }

    public void addVideo(Video video){
        library.add(video);

    }
}
