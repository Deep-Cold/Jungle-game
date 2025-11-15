package Model.Logging;

import Model.Piece;
import Model.Board;
import Elements.Coordinate;

public class Event {
    private final EventType type;
    public Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}

abstract class MoveEvent extends Event {
    private final Coordinate originalCoordinate;
    private final Piece targetPiece;
    private final Board belBoard;

    protected MoveEvent(Coordinate _originalCoordinate, Piece _targetPiece, Board _belBoard, EventType _type) {
        super(_type);
        originalCoordinate = _originalCoordinate;
        targetPiece = _targetPiece;
        belBoard = _belBoard;
    }

    public Coordinate getOriginalCoordinate() {
        return originalCoordinate;
    }

    public Piece getTargetPiece() {
        return targetPiece;
    }

    public Board getBoard() {
        return belBoard;
    }

    public abstract void withdraw();

}

class Captured extends MoveEvent {
    public Captured(Coordinate _originalCoordinate, Piece _targetPiece, Board _belBoard) {
        super(_originalCoordinate, _targetPiece, _belBoard, EventType.Captured);
    }
    public void withdraw() {
        Board curBoard = super.getBoard();
        super.getTargetPiece().setAlive();
        curBoard.getSquare(super.getOriginalCoordinate()).setPiece(super.getTargetPiece());
    }
}

class Move extends MoveEvent {
    private final boolean success; // True for success
    private final Coordinate targetCoordinate;
    public Move(Coordinate _originalCoordinate, Piece _targetPiece, Board _belBoard, boolean _success, Coordinate _targetCoordinate) {
        super(_originalCoordinate, _targetPiece, _belBoard, EventType.Move);
        success = _success;
        targetCoordinate = _targetCoordinate;
    }

    public void withdraw() {
        if(!success) {
            throw new IllegalCallerException("You can not withdraw a unsuccessful move");
        }
        Board curBoard = super.getBoard();
        curBoard.getSquare(targetCoordinate).setPiece(null);
        curBoard.getSquare(super.getOriginalCoordinate()).setPiece(super.getTargetPiece());
    }
}

class Withdraw extends Event {
    private final boolean turn;
    public Withdraw(boolean _turn) {
        super(EventType.Withdraw);
        turn = _turn;
    }
    public boolean getTurn() {
        return turn;
    }
}