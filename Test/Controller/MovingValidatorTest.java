package Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Board.Board;

import static org.junit.jupiter.api.Assertions.*;

class MovingValidatorTest {
    private static Board board;
    private static MovingValidator movingValidator;

    @BeforeAll
    public static void init() {
        board = new Board();
        movingValidator = new MovingValidator(board);
    }

    @Test
    public void testSize() {
        assertEquals(8, movingValidator.getUpperDictionary().size());
        assertEquals(8, movingValidator.getLowerDictionary().size());
    }
}