package memorygame.domain;

public class Board {

    private Card[][] deck;
    private Card previous;
    private Card current;
    private int flippedCards;
    private int foundPairs;

    public Board() {
        this.deck = new Card[4][3]; // deck of 12 cards
        this.flippedCards = 0;
        this.foundPairs = 0;
    }

    public Card getCard(int row, int column) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == row && j == column) {
                    this.current = this.deck[row][column];
                }
            }
        }
        return this.current;
    }

    public Card[][] getDeck() {
        return deck;
    }

    public void setPreviouscard(Card previousCard) {
        this.previous = previousCard;
    }

    public Card getPreviouscard() {
        return this.previous;
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
