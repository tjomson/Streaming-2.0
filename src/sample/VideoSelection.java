package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Denne klasse udgør den primære side hvor videoer vises.

public class VideoSelection {

    private Model model;

    private TextField searchField = new TextField();
    private ComboBox<String> sortingOptions = new ComboBox<>();
    private ComboBox<String> genreOptions = new ComboBox<>();
    private CheckBox movieCheckBox = new CheckBox();
    private CheckBox seriesCheckBox = new CheckBox();
    private CheckBox myListCheckBox = new CheckBox();
    private Button changeUserButton = new Button("Skift bruger");
    private Stage mainStage;

    public void openStartScene() throws IOException, loggedInAsGuestException {

        try {

            model = Model.getInstance();

            FlowPane flowPane = new FlowPane();
            flowPane.setVgap(10);
            flowPane.setHgap(10);
            flowPane.setAlignment(Pos.CENTER);

            //De videoer som vise når vinduet åbnes findes.
            List<Video> videos = new SearchEngine().getSearchItems("", "Titel: A-Å", "Alle", true, true, false);

            //Hver videos VBox tilføjes til FlowPane'et
            for (Video video : videos) {
                VBox vBox = video.toVBox();
                flowPane.getChildren().add(vBox);
            }

            //ScrollPane'et sættes til at indeholde FlowPane'et
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(flowPane);
            scrollPane.setFitToWidth(true);

            VBox window = new VBox();
            Label userNameLabel = new Label(" Du er logget ind som: " + model.getUserName());

            FlowPane topBar = new FlowPane();

            topBar.setVgap(10.0);
            topBar.setHgap(10.0);

            //Når man ændrer på et felt, opdateres videoerne.
            searchField.setOnKeyTyped(keyEvent -> updateVideoSelection(flowPane));
            movieCheckBox.setOnAction(actionEvent -> updateVideoSelection(flowPane));
            seriesCheckBox.setOnAction(actionEvent -> updateVideoSelection(flowPane));
            myListCheckBox.setOnAction(actionEvent -> updateVideoSelection(flowPane));
            genreOptions.setOnAction(actionEvent -> updateVideoSelection(flowPane));
            sortingOptions.setOnAction(actionEvent -> updateVideoSelection(flowPane));

            //changeUserButton skal sende en hen til siden hvor man kan ændre bruger.
            changeUserButton.setOnAction(actionEvent -> {
                ChooseUserScreen c = new ChooseUserScreen();
                try {
                    c.chooseUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                model.getMainStage().close();
            });

            //Alle sorteringsmuligheder tilføjes, og som standard vises "Titel: A-Å".
            sortingOptions.getItems().addAll("Titel: A-Å", "Titel: Å-A", "Årstal: Ny-Gammel", "Årstal: Gammel-Ny", "Vurdering: Bedst-Dårligst", "Vurdering: Dårligst-Bedst");
            sortingOptions.getSelectionModel().select(0);

            //Genrerne i hver video tjekkes. Hvis listen ikke allerede har den genre, tilføjes den.
            List<String> genres = new ArrayList<>();
            for (Video video : videos) {
                for (String genre : video.getGenres()) {
                    if (!genres.contains(genre)) {
                        genres.add(genre);
                    }
                }
            }

            //Genrerne tilføjes og muligheden "Alle" indsættes først og sættes til at være standard.
            genreOptions.getItems().add("Alle");
            genreOptions.getItems().addAll(genres);
            genreOptions.getSelectionModel().select(0);

            //Hvordan checkboxene som standard skal være sættes.
            movieCheckBox.setSelected(true);
            seriesCheckBox.setSelected(true);
            myListCheckBox.setSelected(false);

            //Elementerne til topBar tikføjes.
            topBar.getChildren().addAll(
                    new Label(" Søg på titel:"),
                    searchField,
                    new Label(" Sorter efter:"),
                    sortingOptions,
                    new Label(" Genre:"),
                    genreOptions,

                    new Label(" Vis film"),
                    movieCheckBox,
                    new Label(" Vis serier"),

                    seriesCheckBox,
                    new Label(" På min liste"),
                    myListCheckBox,
                    changeUserButton);

            window.getChildren().addAll(userNameLabel, topBar, scrollPane);
            window.setSpacing(10);

            mainStage = new Stage();
            mainStage.setTitle("GOAT");
            mainStage.getIcons().add(new Image("/blackSquare.png"));
            model.addMainStage(mainStage);
            mainStage.setScene(new Scene(window, 1200, 600));
            mainStage.show();

        } catch (noSuchVideoException e) {
            System.out.println(e.getMessage());
        }
    }

    //Metode til at opdatere videoerne som vises.
    public void updateVideoSelection(FlowPane flowPane) {
        try {
            flowPane.getChildren().clear();
            List<Video> searchedVideos = new SearchEngine().getSearchItems(searchField.getText(), sortingOptions.getValue(), genreOptions.getValue(), movieCheckBox.isSelected(), seriesCheckBox.isSelected(), myListCheckBox.isSelected());
            for (Video video : searchedVideos) {
                VBox vBox = video.toVBox();
                flowPane.getChildren().add(vBox);
            }

        } catch (IOException | noSuchVideoException | loggedInAsGuestException e) {
            flowPane.getChildren().add(new Label(e.getMessage()));
        }

    }

}