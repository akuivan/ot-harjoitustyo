package memorygame.domain;

/**
 * Board class takes care of game logic along with Card class and
 * keeps track of card objects.
 */
public class Board {

    private Card[][] deck;
    private Card previous;
    private Card current;
    private int flippedCards;
    private int foundPairs;

    /**
     * Creates Board object.
     *
     */
    public Board() {
        this.deck = new Card[4][3]; // deck of 12 cards
        this.flippedCards = 0;
        this.foundPairs = 0;
    }

    /**
     * Get Card object by its position.
     *
     * @param row is a value inserted in Memorygame.Ui. It represents card's
     * position inside 2d array
     *
     * @param column is a value inserted in Memorygame.Ui. It represents card's
     * position inside 2d array
     * @return current card
     */
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

    /**
     * Set new value to previous -variable by parameter previousCard.
     *
     * @param previousCard is a value inserted in Controller that represents a
     * previously flipped card.
     */
    public void setPreviouscard(Card previousCard) {
        this.previous = previousCard;
    }

    public Card getPreviouscard() {
        return this.previous;
    }

    /**
     * Resets the value of flippedCards to 0. This method is called in
     * Controller when two cards have been flipped or same card has been flipped
     * twice.
     */
    public void resetFlippedCards() {
        this.flippedCards = 0;
    }

    public int getFlippedCards() {
        return flippedCards;
    }

    public int getFoundPairs() {
        return foundPairs;
    }

    /**
     * This method is called in Controller, when a card has been flipped. It
     * increases the value of varibale flippedCards by 1.
     */
    public void increaseFlippedCards() {
        this.flippedCards++;
    }

    /**
     * This method is called in Controller, when matching cards have been found.
     * It increases the value of varibale foundPairs by 1.
     */
    public void increaseFoundPairs() {
        this.foundPairs++;
    }

}
