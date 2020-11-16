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
    public void resetFoundPairsResetsValueToZero() {
        gameboard.increaseFoundPairs();
        gameboard.increaseFoundPairs();
        gameboard.resetFoundPairs();

        assertTrue(0 == gameboard.getFoundPairs());
    }
    @Test
    public void increaseFlippedCardsIncreasesValueByOne() {
        gameboard.increaseFlippedCards();
        gameboard.increaseFlippedCards();
        assertTrue(2 == gameboard.getFlippedCards());
    }
    
}
