package memorygame.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board gameboard;

    @Before
    public void setUp() {
        gameboard = new Board();
    }

    @Test
    public void createdBoardExists() {
        assertTrue(gameboard != null);
    }

    @Test
    public void resetFlippedCardsResetsValueToZero() {
        gameboard.increaseFlippedCards();
        gameboard.increaseFlippedCards();
        gameboard.resetFlippedCards();

        assertTrue(0 == gameboard.getFlippedCards());
    }

    @Test
    public void increaseFlippedCardsIncreasesValueByOne() {
        gameboard.increaseFlippedCards();
        gameboard.increaseFlippedCards();
        assertTrue(2 == gameboard.getFlippedCards());
    }

    @Test
    public void getCardReturnsCorrectCard() {
        gameboard.getDeck()[0][0] = new Card(0, 0);

        assertTrue(gameboard.getDeck()[0][0] == gameboard.getCard(0, 0));

    }

    @Test
    public void getFoundPairsReturnCorrectValue() {
        assertTrue(0 == gameboard.getFoundPairs());
    }

    @Test
    public void increaseFoundPairsIncreasesValueByOne() {
        gameboard.increaseFoundPairs();
        gameboard.increaseFoundPairs();
        gameboard.increaseFoundPairs();

        assertTrue(3 == gameboard.getFoundPairs());
    }
}
