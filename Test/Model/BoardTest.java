package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testMoving1() {
        assertFalse(board.attemptMove("tiger", false, 'D'));
        assertTrue(board.attemptMove("tiger", false, 'U'));
        assertFalse(board.attemptMove("tiger", false, 'U'));

        assertTrue(board.attemptMove("elephant", false, 'U'));
        assertFalse(board.attemptMove("elephant", false, 'R'));

        assertTrue(board.attemptMove("lion", false, 'L'));
        assertTrue(board.attemptMove("lion", false, 'L'));
        assertFalse(board.attemptMove("lion", false, 'L'));
    }

    @Test
    public void testMoving2() {
        assertTrue(board.attemptMove("elephant", false, 'U'));
        assertTrue(board.attemptMove("elephant", false, 'U'));
        assertTrue(board.attemptMove("elephant", false, 'U'));
        assertFalse(board.attemptMove("elephant", false, 'U'));

        assertTrue(board.attemptMove("tiger", false, 'U'));
        assertTrue(board.attemptMove("tiger", false, 'U'));
        assertTrue(board.attemptMove("tiger", false, 'R'));
        assertTrue(board.attemptMove("tiger", false, 'U'));
    }

    @Test
    public void testMoving3() {
        assertTrue(board.attemptMove("rat", false, 'L'));
        assertTrue(board.attemptMove("rat", false, 'U'));
        assertTrue(board.attemptMove("rat", false, 'U'));
        assertTrue(board.attemptMove("rat", false, 'U'));
        assertTrue(board.attemptMove("elephant", true, 'L'));
        assertFalse(board.attemptMove("rat", false, 'U'));
    }

    @Test
    public void testMoving4() {
        assertTrue(board.attemptMove("leopard", false, 'L'));
        assertTrue(board.attemptMove("leopard", false, 'U'));
        assertTrue(board.attemptMove("leopard", false, 'U'));
        assertTrue(board.attemptMove("leopard", false, 'U'));
        assertTrue(board.attemptMove("leopard", false, 'U'));
        assertTrue(board.attemptMove("leopard", false, 'U'));
        assertTrue(board.attemptMove("leopard", false, 'U'));
    }

}