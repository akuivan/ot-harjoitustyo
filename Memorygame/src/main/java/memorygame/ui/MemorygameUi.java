package memorygame.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import memorygame.db.Database;
import memorygame.domain.Board;
import memorygame.domain.Card;

/**
 * MemorygameUi class takes care of UI outside the actual game (main menu and
 * score's) by rendering view based on user's mouse clicks.
 */
public class MemorygameUi extends Application {

    private static EventHandler<MouseEvent> handler;
    static GridPane setting;
    static ArrayList<Image> pictureDeck;
    static Image[] pictures;
    static Image back;
    static Button[][] buttons;
    static Board board;
    static int timeInSeconds;
    static Timer timer;
    static Stage scoreStage;
    static Stage gameStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//      initialize database
        new Database().createDatabase();

        this.handler = new Controller();
        GridPane menuButtons = new GridPane();
        Button startGame = new Button("Aloita peli");
        Button viewLeaderboard = new Button("Ennätykset");
        Button quitGame = new Button("Sulje peli");
        menuButtons.add(startGame, 0, 0);
        menuButtons.add(viewLeaderboard, 0, 1);
        menuButtons.add(quitGame, 0, 2);
        menuButtons.setAlignment(Pos.CENTER);
        Scene menu = new Scene(menuButtons);

        Button goToMenu = new Button("Palaa päävalikkoon");

        startGame.setOnAction((event) -> {
            try {
//              Set timer and start it
                Label clock = new Label();
                this.timeInSeconds = 0;
                this.timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                clock.setText(Integer.toString(timeInSeconds++)
                                        + " sekuntia");
                            }
                        });
                    }
                }, 0, 1000);
                this.setting = new GridPane();
                this.setting.setMinSize(200, 110);
                this.setting.addColumn(6, goToMenu);
                this.setting.add(clock, 6, 1);
                initializeGame();
                Scene game = new Scene(this.setting);
                this.gameStage = new Stage();
                gameStage.setScene(game);
                gameStage.show();
                primaryStage.hide();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(MemorygameUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        goToMenu.setOnAction((event) -> {
            // cancel timer from previous game and hide gamewindow
            if (timer != null) {
                timer.cancel();
                gameStage.hide();
            }
            // hide leaderboard window if open
            if (scoreStage != null) {
                scoreStage.hide();
            }
            primaryStage.setScene(menu);
            primaryStage.show();
        });

        viewLeaderboard.setOnAction((event) -> {
            // create separate thread for scores
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    VBox scores = new VBox();
                    scores.setAlignment(Pos.CENTER);
                    scores.getChildren().add(new Text("Top 3 \n"));
                    new Database().getScoresFromDatabase(scores);
                    scores.getChildren().add(goToMenu);
                    Scene leaderboard = new Scene(scores);
                    scoreStage = new Stage();
                    scoreStage.setScene(leaderboard);
                    scoreStage.show();
                    primaryStage.hide();
                }
            });
        });

        quitGame.setOnAction((event) -> {
            primaryStage.close();
        });

        primaryStage.setScene(menu);
        primaryStage.show();
    }

    public void initializeGame() throws FileNotFoundException {
        createBoard();
        initializePictures();
        initializeCardDeck(this.setting);
    }

    public static EventHandler<MouseEvent> gameController() {
        return handler;
    }

    public void createBoard() {
        this.board = new Board();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                board.getDeck()[i][j] = new Card(i, j);
            }
        }
    }

    public void initializePictures() {
//      Get images
        this.back = new Image(getClass().getClassLoader().getResourceAsStream("basket.png"));

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
//      Shuffle cards' images
        Collections.shuffle(pictureDeck);

//      Assign an image to a card
        int pictureDeckIndex = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                this.board.getCard(i, j).setImage(pictureDeck.get(pictureDeckIndex).toString());
                pictureDeckIndex++;
            }
        }
    }

    public void initializeCardDeck(GridPane setting) throws FileNotFoundException {
//      draw actual gameboard
        this.buttons = new Button[4][3]; // deck of 12 cards

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j] = new Button();
                this.buttons[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, gameController());
                setBackPicture(this.back, buttons[i][j]);
                setting.add(buttons[i][j], i, j);
            }
        }
    }

    public void setBackPicture(Image picture, Button button) {
        ImageView view = new ImageView(picture);
        view.setFitHeight(20);
        view.setFitWidth(20);
        button.setGraphic(view);
    }

}
