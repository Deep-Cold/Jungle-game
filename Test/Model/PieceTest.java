package Model;

import Model.Elements.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    private Piece piece;
    private Square square;

    @BeforeEach
    public void setUp() {
        piece = new Piece(PieceType.Rat, false);
        square = new Square(new Model.Elements.Coordinate(1, 1), Model.Elements.SquareType.Normal);
    }

    @Test
    public void testConstructor() {
        assertNotNull(piece);
        assertEquals(PieceType.Rat, piece.getType());
        assertFalse(piece.getBelongs());
        assertTrue(piece.getStatus());
    }

    @Test
    public void testSetPosition() {
        piece.setPosition(square);
        assertEquals(square, piece.getPosition());
    }

    @Test
    public void testSetDie() {
        piece.setPosition(square);
        assertTrue(piece.getStatus());
        piece.setDie();
        assertFalse(piece.getStatus());
        assertNull(piece.getPosition());
    }

    @Test
    public void testSetAlive() {
        piece.setDie();
        assertFalse(piece.getStatus());
        piece.setAlive();
        assertTrue(piece.getStatus());
    }

    @Test
    public void testGetType() {
        Piece elephant = new Piece(PieceType.Elephant, true);
        assertEquals(PieceType.Elephant, elephant.getType());
    }

    @Test
    public void testGetBelongs() {
        Piece upperPiece = new Piece(PieceType.Lion, true);
        assertTrue(upperPiece.getBelongs());
        
        Piece lowerPiece = new Piece(PieceType.Tiger, false);
        assertFalse(lowerPiece.getBelongs());
    }
}

