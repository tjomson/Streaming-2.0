package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class VideoInfo {

    Model model;
    Stage currentStage;

    public void openVideoInfoScene(Video video) throws IOException{
        model = Model.getInstance();

        HBox  imageInfoBox = new HBox();
        imageInfoBox.setSpacing(20);
        VBox infoBox = new VBox();
        VBox window = new VBox();
        infoBox.setSpacing(20);
        window.setSpacing(20);

        FileInputStream f;
        if (video instanceof Movie) {
            f = new FileInputStream("Billeder/" + video.getTitle() + ".jpg");
        } else {
            f = new FileInputStream("Serier - billeder/" + video.getTitle() + ".jpg");
        }
        Image image = new Image(f);
        ImageView imageView = new ImageView(image);

        Label yearLabel;
        if (video instanceof Series) {
            Series series1 = (Series) video;
            String endYearString;
            if (series1.getEndYear() == 0) {
                endYearString = "";
            } else {
                endYearString = series1.getEndYear() + "";
            }
            yearLabel = new Label("Year: " + video.getYear() + "-" + endYearString);
        } else {
            yearLabel = new Label("Year: " + video.getYear());
        }

        HBox genreField = new HBox();
        genreField.getChildren().add(new Label("Genres: "));
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        infoBox.getChildren().addAll(new Label("Title: " + video.getTitle()),yearLabel,genreField,new Label("Rating: " + video.getRating() + ""));

        imageInfoBox.getChildren().addAll(imageView,infoBox);

        File videoSource = new File("video example.mp4");
        Media media = new Media(videoSource.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button("Play");
        Button muteButton = new Button("Mute");
        Button addToMyListButton = new Button();

        /*boolean alreadyAdded = false;
        for(Video v : new SearchEngine().getMyListVideos(model.getUserID())){
            if(video.getTitle().equals(v.getTitle())){
                alreadyAdded = true;
            }
        }

        if(alreadyAdded){
            addToMyListButton.setText("Remove from My List");
        }
        else{
            addToMyListButton.setText("Add to My List");
        }

        //DET HER FUNGERER IKKE FULDT UD
        addToMyListButton.setOnAction(actionEvent -> {
            SearchEngine s = new SearchEngine();
            if(addToMyListButton.getText().equals("Remove from My List")){
                s.removeVideoFromMyList(video);
                addToMyListButton.setText("Add to My List");
                try {
                    s.listToFile((ArrayList<Video>) s.getMyListVideos(model.getUserID()),"MyListUser" + model.getUserID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                s.addVideoToMyList(video);
                addToMyListButton.setText("Remove from My List");
                try {
                    s.listToFile((ArrayList<Video>) s.getMyListVideos(model.getUserID()),"MyListUser" + model.getUserID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
*/

        muteButton.setOnAction(actionEvent -> {
            if(muteButton.getText().equals("Mute")){
                mediaPlayer.setMute(true);
                muteButton.setText("Unmute");
            }
            else{
                mediaPlayer.setMute(false);
                muteButton.setText("Mute");
            }
        });

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(playButton,muteButton,addToMyListButton);

        playButton.setOnAction(actionEvent -> {
            if(playButton.getText().equals("Play")) {
                mediaPlayer.play();
                playButton.setText("Pause");
            }
            else{
                mediaPlayer.pause();
                playButton.setText("Play");
            }
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            playButton.setText("Play");
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.pause();
        });

        if(video instanceof Series){
            Label nowPlayingLabel = new Label("Episode selected: Season 1 episode 1");

            Series series = (Series) video;
            ScrollPane scrollPane = new ScrollPane();
            VBox seasonsBox = new VBox();
            scrollPane.setContent(seasonsBox);
            scrollPane.setFitToWidth(true);
            infoBox.getChildren().add(new Label("Number of seasons: " + series.getSeasons().size()));

            for(Map.Entry entry : series.getSeasons().entrySet()){

                FlowPane episodesFlowPane = new FlowPane();
                int numberOfEpisodes = (int) entry.getValue();
                for(int i = 1; i <= numberOfEpisodes; i++){
                    Button button = new Button("Episode " + i);
                    final int finalInt = i; //En label kan kun tage en final int.
                    button.setOnAction(actionEvent -> {
                                nowPlayingLabel.setText("Episode selected: Season " + entry.getKey() + " episode " + finalInt);
                                mediaPlayer.seek(Duration.millis(0));
                            }
                    );

                    episodesFlowPane.getChildren().add(button);
                }
                seasonsBox.getChildren().addAll(new Label("Season " + entry.getKey()),episodesFlowPane);
            }
            window.getChildren().addAll(mediaView,nowPlayingLabel,buttons,imageInfoBox,scrollPane);

        }
        else{
            window.getChildren().addAll(mediaView,buttons,imageInfoBox);
        }

        currentStage = new Stage();
        model.addCurrentStage(currentStage);
        currentStage.setScene(new Scene(window, 700, 900));
        currentStage.setTitle(video.getTitle());
        currentStage.show();
    }
}
