package Model.Logging;

import Model.Board;
import Model.Elements.Coordinate;
import Model.Piece;
import Model.Elements.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
    private Board board;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        board = new Board();
        logger = board.getLogger();
    }

    @Test
    public void testAddMove() {
        board.attemptMove("rat", false, 'L');
        assertNotNull(logger);
    }

    @Test
    public void testAddCaptured() {
        board.attemptMove("elephant", false, 'U');
        board.attemptMove("elephant", false, 'U');
        board.attemptMove("elephant", false, 'U');
        
        board.attemptMove("elephant", false, 'R');
        assertNotNull(logger);
    }

    @Test
    public void testTryWithdraw() {
        board.attemptMove("rat", false, 'L');
        board.attemptMove("cat", true, 'D');
        
        assertDoesNotThrow(() -> board.tryWithdraw(false));
    }

    @Test
    public void testTryWithdrawNotEnoughMoves() {
        board.attemptMove("rat", false, 'L');
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.tryWithdraw(false);
        });
    }

    @Test
    public void testInitReplay() {
        board.attemptMove("rat", false, 'L');
        board.attemptMove("cat", false, 'U');
        
        assertDoesNotThrow(() -> {
            logger.initReplay();
        });
    }

    @Test
    public void testNextStep() {
        board.attemptMove("rat", false, 'L');
        board.attemptMove("cat", false, 'U');
        
        logger.initReplay();
        
        assertDoesNotThrow(() -> {
            boolean result = logger.nextStep();
            assertTrue(result);
        });
    }

    @Test
    public void testNextStepLastStep() {
        board.attemptMove("rat", false, 'L');
        
        logger.initReplay();
        logger.nextStep();
        
        assertThrows(IllegalStateException.class, () -> {
            logger.nextStep();
        });
    }

    @Test
    public void testPreviousStep() {
        board.attemptMove("rat", false, 'L');
        
        logger.initReplay();
        logger.nextStep();
        
        assertDoesNotThrow(() -> {
            boolean result = logger.previousStep();
            assertTrue(result);
        });
    }

    @Test
    public void testPreviousStepAtStart() {
        board.attemptMove("rat", false, 'L');
        
        logger.initReplay();
        
        assertThrows(IllegalStateException.class, () -> {
            logger.previousStep();
        });
    }
}

