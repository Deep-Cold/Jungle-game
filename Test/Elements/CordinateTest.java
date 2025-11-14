package Elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    private Coordinate origin;
    private Coordinate destination;

    @BeforeEach
    public void setUp() {
        origin = new Coordinate(0,0);
    }

    @Test
    public void testMoveX() {
        origin.changeX(-5);
        destination = new Coordinate(-5,0);
        assertEquals(origin, destination);
    }

    @Test
    public void testMoveY() {
        origin.changeY(5);
        destination = new Coordinate(0,5);
        assertEquals(origin, destination);
    }

    @Test
    public void testCheckBound() {
        origin = new Coordinate(10,5);
        assertTrue(origin.checkBound(10, 10));

        origin = new Coordinate(10,5);
        assertFalse(origin.checkBound(5, 5));

        origin = new Coordinate(5,5);
        assertTrue(origin.checkBound(5, 5));

        origin = new Coordinate(0,5);
        assertFalse(origin.checkBound(10, 10));
    }
}