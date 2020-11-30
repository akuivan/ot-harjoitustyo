package memorygame.ui;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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

        if (currentCard.getCardIsFlipped() == true //      handle same card
                & currentCard == MemorygameUi.board.getPreviouscard()) {
            flipTheSameCardOver(currentCard, currentButton);
        } else if (MemorygameUi.board.getFlippedCards() == 2) { //  handle second card
            flipCard(currentCard, currentButton);
            MemorygameUi.board.resetFlippedCards();
            // if cards match
            if (currentCard.getImage().equals(MemorygameUi.board.getPreviouscard().getImage())) {
                removeMatchingCards(currentButton, previousButton);
                // otherwise
            } else {
                flipBothCardsOver(currentCard, MemorygameUi.board.getPreviouscard(), currentButton, previousButton);
            }
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

    public void flipTheSameCardOver(Card current, Button button) {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
        pause.setOnFinished(e -> {
            flipCard(current, button);
            MemorygameUi.board.resetFlippedCards();
            // during pause other cards cannot be flipped
            button.getParent().setDisable(false);
        });
        button.getParent().setDisable(true);
        pause.play();
    }

    public void removeMatchingCards(Button currentButton, Button previousButton) {
        MemorygameUi.board.increaseFoundPairs();
        FadeTransition ft1 = new FadeTransition(Duration.seconds(0.7), currentButton);
        FadeTransition ft2 = new FadeTransition(Duration.seconds(0.7), previousButton);
        ft1.setToValue(0);
        ft1.play();
        ft2.setToValue(0);
        ft2.play();

        if (MemorygameUi.board.getFoundPairs() == 6) {
//          stop timer in game
            MemorygameUi.timer.cancel();
            Button saveScore = new Button("Tallenna ennätys");
            Text text = new Text();
            text.setText("Peli päättyi!");
            MemorygameUi.setting.add(text, 6, 2);
            MemorygameUi.setting.add(saveScore, 6, 3);
            saveScore.setOnAction((event) -> {
                // save to database
                System.out.println("Toivon mukaan tallentaa ennätyksen" +
                        " ensi viikon versiossa");
            });
        }
    }

    public void flipBothCardsOver(Card currentCard, Card previousCard, Button currentButton, Button previousButton) {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
        pause.setOnFinished(e -> {
            flipCard(currentCard, currentButton);
            flipCard(previousCard, previousButton);

            // during pause other cards cannot be flipped
            currentButton.getParent().setDisable(false);
        });
        currentButton.getParent().setDisable(true);
        pause.play();

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
