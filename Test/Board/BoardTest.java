package Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Elements.PieceType;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testMoving1() {
        assertFalse(board.temptMove("tiger", false, 'D'));
        assertTrue(board.temptMove("tiger", false, 'U'));
        assertFalse(board.temptMove("tiger", false, 'U'));

        assertTrue(board.temptMove("elephant", false, 'U'));
        assertFalse(board.temptMove("elephant", false, 'R'));
    }

    @Test
    public void testMoving2() {
        assertTrue(board.temptMove("elephant", false, 'U'));
        assertTrue(board.temptMove("elephant", false, 'U'));
        assertTrue(board.temptMove("elephant", false, 'U'));
        assertFalse(board.temptMove("elephant", false, 'U'));

        assertTrue(board.temptMove("tiger", false, 'U'));
        assertTrue(board.temptMove("tiger", false, 'U'));
        assertTrue(board.temptMove("tiger", false, 'R'));
        assertTrue(board.temptMove("tiger", false, 'U'));
    }

    @Test
    public void testMoving3() {
        assertTrue(board.temptMove("rat", false, 'L'));
        assertTrue(board.temptMove("rat", false, 'U'));
        assertTrue(board.temptMove("rat", false, 'U'));
        assertTrue(board.temptMove("rat", false, 'U'));

    }
}