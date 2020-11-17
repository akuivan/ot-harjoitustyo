package memorygame.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {

    private String image;
    private int row;
    private int column;
    private boolean cardIsFlipped;

    public Card(int row, int column) {
        this.row = row;
        this.column = column;
        this.cardIsFlipped = false;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getCardIsFlipped() {
        return this.cardIsFlipped;
    }

    public void setCardIsFlipped(boolean cardIsFlipped) {
        this.cardIsFlipped = cardIsFlipped;
    }

}
