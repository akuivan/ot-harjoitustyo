package memorygame.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends Button {

    private boolean cardIsFlipped;
    private Image back;
    private Image front;

    public Card(Image front) throws FileNotFoundException {
        this.cardIsFlipped = false;
        this.front = front;
        this.back = new Image(getClass().getClassLoader().getResourceAsStream("basket.png"));
        setPicture(this.back);
    }

    public boolean getCardIsFlipped() {
        return this.cardIsFlipped;
    }

    public void setCardIsFlipped(boolean cardIsFlipped) {
        this.cardIsFlipped = cardIsFlipped;
    }

    public Image getBackImage() {
        return back;
    }

    public void setPicture(Image picture) {
        ImageView view = new ImageView(picture);
        view.setFitHeight(20);
        view.setFitWidth(20);
        this.setGraphic(view);
    }

    public void turnCard() {
        if (this.cardIsFlipped) {
            setCardIsFlipped(false);
            setPicture(this.back);

        } else {
            setCardIsFlipped(true);
            setPicture(this.front);
        }
    }

}
