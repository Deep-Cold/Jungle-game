package Model;

import Model.Elements.Coordinate;
import Model.Elements.SquareType;

import java.io.Serializable;

public class Square implements Serializable {
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
        if(piece != null) {
            piece.setPosition(this);
        }
    }

    public Piece getPiece() {
        return currentPiece;
    }
}
