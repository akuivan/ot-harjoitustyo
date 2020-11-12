package memorygame.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MemorygameUi extends Application {

    private static EventHandler<MouseEvent> handler;
    static GridPane setting;

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
            this.setting = new GridPane();
            this.setting.addColumn(6, goToMenu);
            Scene game = new Scene(this.setting);
            primaryStage.setScene(game);
        });

        goToMenu.setOnAction((event) -> {
            primaryStage.setScene(menu);
        });

        primaryStage.setScene(menu);
        primaryStage.show();

    }

    public static EventHandler<MouseEvent> gameController() {
        return handler;
    }

}
