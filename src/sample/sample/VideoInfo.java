package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.util.Map;

public class VideoInfo {


    private Model model;
    private Stage currentStage;

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
            yearLabel = new Label("Årstal: " + video.getYear() + "-" + endYearString);
        } else {
            yearLabel = new Label("Årstal: " + video.getYear());
        }

        HBox genreField = new HBox();
        genreField.getChildren().add(new Label("Genre: "));
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        infoBox.getChildren().addAll(new Label("Titel: " + video.getTitle()),yearLabel,genreField,new Label("Vurdering: " + video.getRating() + ""));

        imageInfoBox.getChildren().addAll(imageView,infoBox);

        File videoSource = new File("video example.mp4");
        Media media = new Media(videoSource.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button("Afspil");
        Button muteButton = new Button("Mute");

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
        buttons.getChildren().addAll(playButton,muteButton);

        if(model.getUserID()!= 0) {
            Button addToMyListButton = new Button();

            boolean isOnMyList = false;
            for (Video v : model.getMyList()) {
                if (v.getTitle().equals(video.getTitle())) {
                    isOnMyList = true;
                }
            }

            if (isOnMyList) {
                addToMyListButton.setText("Fjern fra Min Liste");
            } else {
                addToMyListButton.setText("Tilføj til Min Liste");
            }

            addToMyListButton.setOnAction(actionEvent -> {
                if (addToMyListButton.getText().equals("Tilføj til Min Liste")) {
                    model.addToMyList(video);
                    addToMyListButton.setText("Fjern fra Min Liste");
                } else {
                    model.removeFromMyList(video);
                    addToMyListButton.setText("Tilføj til Min Liste");
                }

            });
            buttons.getChildren().add(addToMyListButton);
        }

        playButton.setOnAction(actionEvent -> {
            if(playButton.getText().equals("Afspil")) {
                mediaPlayer.play();
                playButton.setText("Pause");
            }
            else{
                mediaPlayer.pause();
                playButton.setText("Afspil");
            }


        });

        mediaPlayer.setOnEndOfMedia(() -> {
            playButton.setText("Afspil");
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.pause();
        });

        if(video instanceof Series){
            Label nowPlayingLabel = new Label("Episode valgt: Sæson 1 episode 1");

            Series series = (Series) video;
            ScrollPane scrollPane = new ScrollPane();
            VBox seasonsBox = new VBox();
            scrollPane.setContent(seasonsBox);
            scrollPane.setFitToWidth(true);
            infoBox.getChildren().add(new Label("Antal sæsoner: " + series.getSeasons().size()));

            for(Map.Entry entry : series.getSeasons().entrySet()){

                FlowPane episodesFlowPane = new FlowPane();
                int numberOfEpisodes = (int) entry.getValue();
                for(int i = 1; i <= numberOfEpisodes; i++){
                    Button button = new Button("Episode " + i);
                    final int finalInt = i; //En label kan kun tage en final int.
                    button.setOnAction(actionEvent -> {
                                nowPlayingLabel.setText("Episode valgt: Sæson " + entry.getKey() + " episode " + finalInt);
                                mediaPlayer.seek(Duration.millis(0));
                            }
                    );

                    episodesFlowPane.getChildren().add(button);
                }
                seasonsBox.getChildren().addAll(new Label("Sæson " + entry.getKey()),episodesFlowPane);
            }
            window.getChildren().addAll(mediaView,nowPlayingLabel,buttons,imageInfoBox,scrollPane);

        }
        else{
            window.getChildren().addAll(mediaView,buttons,imageInfoBox);
        }

        window.setOnMouseDragExited(mouseDragEvent -> mediaPlayer.stop());

        currentStage = new Stage();
      //  currentStage.getIcons().add(new Image("/blackSquare.png"));
        model.addCurrentStage(currentStage);
        currentStage.setOnCloseRequest(windowEvent -> mediaPlayer.stop());
        currentStage.setScene(new Scene(window, 700, 900));
        currentStage.setTitle(video.getTitle());
        currentStage.show();
    }
}
