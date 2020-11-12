package memorygame.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import memorygame.domain.Card;

public class MemorygameUi extends Application {

    private static EventHandler<MouseEvent> handler;
    static GridPane setting;
    static Card[][] deck;
    static ArrayList<Image> pictureDeck;
    static Image[] pictures;
    static int flippedCards;
    static int foundPairs;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.handler = new Controller();
        Button startGame = new Button("Aloita peli");
        Scene menu = new Scene(startGame);
        Button goToMenu = new Button("Palaa päävalikkoon");

        startGame.setOnAction((event) -> {
            try {
                this.setting = new GridPane();
                this.setting.addColumn(6, goToMenu);
                initializeGame();
                Scene game = new Scene(this.setting);
                primaryStage.setScene(game);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MemorygameUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        goToMenu.setOnAction((event) -> {
            primaryStage.setScene(menu);
        });

        primaryStage.setScene(menu);
        primaryStage.show();
    }

    public void initializeGame() throws FileNotFoundException {
        resetFoundPairs();
        resetFlippedCards();
        initializePictures();
        initializeCardDeck(this.setting);
    }

    public static EventHandler<MouseEvent> gameController() {
        return handler;
    }

    public void initializePictures() {
        this.pictures = new Image[6];
        this.pictures[0] = new Image(getClass().getClassLoader().getResourceAsStream("apple.png"));
        this.pictures[1] = new Image(getClass().getClassLoader().getResourceAsStream("grape.png"));
        this.pictures[2] = new Image(getClass().getClassLoader().getResourceAsStream("peach.png"));
        this.pictures[3] = new Image(getClass().getClassLoader().getResourceAsStream("pear.png"));
        this.pictures[4] = new Image(getClass().getClassLoader().getResourceAsStream("pineapple.png"));
        this.pictures[5] = new Image(getClass().getClassLoader().getResourceAsStream("tangerine.png"));

        this.pictureDeck = new ArrayList<>();

        for (int i = 0; i < this.pictures.length; i++) {
            this.pictureDeck.add(this.pictures[i]);
            this.pictureDeck.add(this.pictures[i]);
        }
//      Shuffle cards
        Collections.shuffle(pictureDeck);
    }

    public void initializeCardDeck(GridPane setting) throws FileNotFoundException {
        this.deck = new Card[4][3]; // deck of 12 cards
        int indexOfPictureDeck = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                this.deck[i][j] = new Card(this.pictureDeck.get(indexOfPictureDeck));
                this.deck[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, gameController());
                setting.add(this.deck[i][j], i, j);
                indexOfPictureDeck++;
            }
        }
    }

    public void resetFoundPairs() {
        this.foundPairs = 0;
    }

    public void resetFlippedCards() {
        this.flippedCards = 0;
    }

}
