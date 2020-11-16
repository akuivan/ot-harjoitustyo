package memorygame.domain;

public class Board {

    private Card previous;
    private int flippedCards;
    private int foundPairs;

    public Board() {
        this.flippedCards = 0;
        this.foundPairs = 0;
    }

    public void setPreviouscard(Card previousCard) {
        this.previous = previousCard;
    }

    public void compareCards() {

    }

    public Card getPreviouscard() {
        return this.previous;
    }

    public void resetFoundPairs() {
        this.foundPairs = 0;
    }

    public void resetFlippedCards() {
        this.flippedCards = 0;
    }

    public int getFlippedCards() {
        return flippedCards;
    }

    public int getFoundPairs() {
        return foundPairs;
    }

    public void increaseFlippedCards() {
        this.flippedCards++;
    }

    public void increaseFoundPairs() {
        this.foundPairs++;
    }

}
