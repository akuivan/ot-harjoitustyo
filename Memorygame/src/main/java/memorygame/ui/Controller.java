package memorygame.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import memorygame.domain.Card;
import static memorygame.ui.MemorygameUi.board;

public class Controller implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        Card current = (Card) event.getSource();
        MemorygameUi.board.increaseFlippedCards();

        if (current.getCardIsFlipped() == false) {
            current.turnCard();
            MemorygameUi.board.setPreviouscard(current);
        }

    }

}
