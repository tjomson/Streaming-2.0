package project;

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
import java.io.InputStream;
import java.util.Map;

//Denne klasse udgør hvad der vises når man klikker på en video.

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

        //Videoens billede findes.
        Image image ;
        //Der tjekkes om det er en film eller en serie, da billederne ligger i forskellige mapper.
        if (video instanceof Movie) {
            image = new Image(this.getClass().getResource("/FilmBilleder/" + video.getTitle() + ".jpg").toExternalForm());
        } else {
            image = new Image(this.getClass().getResource("/SerieBilleder/" + video.getTitle() + ".jpg").toExternalForm());

        }
        ImageView imageView = new ImageView(image);

        //En label med årstal laves. Hvis det er en serie, skal slut-årstallet også vises.
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

        //Videoens genrer tilføjes til en HBox så de står vandret efter hinanden.
        HBox genreField = new HBox();
        genreField.getChildren().add(new Label("Genre: "));
        for (String genre : video.getGenres()) {
            genreField.getChildren().add(new Label(genre + " "));
        }

        infoBox.getChildren().addAll(new Label("Titel: " + video.getTitle()),yearLabel,genreField,new Label("Vurdering: " + video.getRating() + ""));

        imageInfoBox.getChildren().addAll(imageView,infoBox);

        //Eksempel-videoen indlæses.
        String videoPath = this.getClass().getResource("/AndreFiler/video example.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        //Knappen til at mute tilføjes.
        Button playButton = new Button("Afspil");
        Button muteButton = new Button("Slå lyd fra");

        muteButton.setOnAction(actionEvent -> {
            if(muteButton.getText().equals("Slå lyd fra")){
                mediaPlayer.setMute(true);
                muteButton.setText("Slå lyd til");
            }
            else{
                mediaPlayer.setMute(false);
                muteButton.setText("Slå lyd fra");
            }
        });

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(playButton,muteButton);

        //Hvis man er logget ind, har man et ID som ikke er 0.
        if(model.getUserID()!= 0) {
            Button addToMyListButton = new Button();

            boolean isOnMyList = false;
            for (Video v : model.getMyList(model.getCurrentUserNumber())) {
                if (v.getTitle().equals(video.getTitle())) { //Der benyttes titel-sammenligning, da det ikke virkede at sammenligne objekter direkte.
                    isOnMyList = true;
                }
            }

            //Knap til at fjerne/tilføje til ens liste.
            if (isOnMyList) {
                addToMyListButton.setText("Fjern fra Min Liste");
            } else {
                addToMyListButton.setText("Tilføj til Min Liste");
            }

            addToMyListButton.setOnAction(actionEvent -> {
                if (addToMyListButton.getText().equals("Tilføj til Min Liste")) {
                    model.addToMyList(video,model.getCurrentUserNumber());
                    addToMyListButton.setText("Fjern fra Min Liste");
                } else {
                    model.removeFromMyList(video,model.getCurrentUserNumber());
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

        //Når filmen er færdig starter den forfra og pauser.
        mediaPlayer.setOnEndOfMedia(() -> {
            playButton.setText("Afspil");
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.pause();
        });

        //Hvis videoen er en serie, vises alle sæsoner og afsnit.
        if(video instanceof Series){
            Label nowPlayingLabel = new Label("Episode valgt: Sæson 1 episode 1");

            //Dette arangeres i en VBox hvor afsnittene i hver sæson tilføjes til et FlowPane.
            Series series = (Series) video;
            ScrollPane scrollPane = new ScrollPane();
            VBox seasonsBox = new VBox();
            scrollPane.setContent(seasonsBox);
            scrollPane.setFitToWidth(true);
            infoBox.getChildren().add(new Label("Antal sæsoner: " + series.getSeasons().size()));

            //For hver sæson tilføjes afsnittene.
            for(Map.Entry entry : series.getSeasons().entrySet()){

                FlowPane episodesFlowPane = new FlowPane();
                int numberOfEpisodes = (int) entry.getValue();
                for(int i = 1; i <= numberOfEpisodes; i++){
                    Button button = new Button("Episode " + i);
                    final int finalInt = i; //En label kan kun tage en final int.
                    button.setOnAction(actionEvent -> {
                                //Når man klikker på et afsnit, starter eksempel-videoen forfra, og der skrives hvilket afsnit som afspiller.
                                nowPlayingLabel.setText("Episode valgt: Sæson " + entry.getKey() + " episode " + finalInt);
                                mediaPlayer.seek(Duration.millis(0));
                                mediaPlayer.pause();
                                playButton.setText("Afspil");

                            }
                    );
                    //Knappen for det afsnit tilføjes.
                    episodesFlowPane.getChildren().add(button);
                }
                seasonsBox.getChildren().addAll(new Label("Sæson " + entry.getKey()),episodesFlowPane);
            }
            window.getChildren().addAll(mediaView,nowPlayingLabel,buttons,imageInfoBox,scrollPane);

        }
        //Hvis det ikke er en serie, og dermed er en film, tilføjes de relevante ting.
        else{
            window.getChildren().addAll(mediaView,buttons,imageInfoBox);
        }

        currentStage = new Stage();
        currentStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("AndreFiler/blackSquare.png")));
        model.addCurrentStage(currentStage);
        currentStage.setOnCloseRequest(windowEvent -> mediaPlayer.stop()); //Videoafspilning stopper når vinduet lukkes.
        currentStage.setScene(new Scene(window, 700, 900));
        currentStage.setTitle(video.getTitle());
        currentStage.show();
    }
}
