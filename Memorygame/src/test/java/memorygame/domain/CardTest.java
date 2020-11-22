package memorygame.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    Card card;

    @Before
    public void setUp() {
        card = new Card(0, 0);
    }

    @Test
    public void getColumnReturnsCorrectValue() {
        assertTrue(0 == card.getColumn());
    }

    @Test
    public void getRowReturnsCorrectValue() {
        assertTrue(0 == card.getRow());
    }

}
