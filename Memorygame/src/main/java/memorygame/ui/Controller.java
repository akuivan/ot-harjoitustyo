package memorygame.ui;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import memorygame.domain.Card;
import static memorygame.ui.MemorygameUi.board;

public class Controller implements EventHandler<MouseEvent> {

    private Button previousButton;

    @Override
    public void handle(MouseEvent event) {
        Button currentButton = (Button) event.getSource();
        MemorygameUi.board.increaseFlippedCards();
//      fetch the card that matches the button 
        Card currentCard = MemorygameUi.board.getCard(getButtonRow(currentButton), getButtonColumn(currentButton));

        if (MemorygameUi.board.getFlippedCards() == 2) { //  handle second card
            flipCard(currentCard, currentButton);
            MemorygameUi.board.resetFlippedCards();
            //handle matches etc.
            //            
            //            
        } else if (currentCard.getCardIsFlipped() == false) { // handle first card
            flipCard(currentCard, currentButton);
            MemorygameUi.board.setPreviouscard(currentCard);
            previousButton = currentButton;
        }

    }

    public void flipCard(Card card, Button button) {
        if (card.getCardIsFlipped()) { //if card is frontside up
            card.setCardIsFlipped(false);
            setPicture(MemorygameUi.back, button);
        } else { //if card is backside up
            card.setCardIsFlipped(true);
            Image image = fetchImage(card.getImage());
            setPicture(image, button);
        }
    }

    public void setPicture(Image picture, Button button) {
        ImageView view = new ImageView(picture);
        view.setFitHeight(20);
        view.setFitWidth(20);
        button.setGraphic(view);
    }

    public Image fetchImage(String image) {
        for (int i = 0; i < MemorygameUi.pictureDeck.size(); i++) {
            if (image.equals(MemorygameUi.pictureDeck.get(i).toString())) {
                return MemorygameUi.pictureDeck.get(i);
            }
        }
        return null;
    }

    public int getButtonRow(Button current) {
        int row = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (current == MemorygameUi.buttons[i][j]) {
                    row = i;
                }
            }
        }
        return row;
    }

    public int getButtonColumn(Button current) {
        int column = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (current == MemorygameUi.buttons[i][j]) {
                    column = j;
                }
            }
        }
        return column;
    }

}
