package Board;

import Elements.Coordinate;
import Elements.PieceType;
import Elements.SquareType;

import java.lang.annotation.ElementType;

public class Square {
    private Coordinate coordinate;
    private SquareType type;
    private Piece currentPiece;

    public Square(Coordinate coordinate, SquareType type) {
        this.coordinate = coordinate;
        this.type = type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public SquareType getType() {
        return type;
    }

    public void setPiece(Piece piece) {
        currentPiece = piece;
        piece.setPosition(this);
    }

    public Piece getPiece() {
        return currentPiece;
    }
}
