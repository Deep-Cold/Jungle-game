package Logging;

import Model.Piece;
import Model.Board;
import Elements.Coordinate;
import Game.Game;

public abstract class Event {
    private final Coordinate originalCoordinate;
    private final Piece targetPiece;
    private final Game belGame;

    protected Event(Coordinate _originalCoordinate, Piece _targetPiece, Game _belGame) {
        originalCoordinate = _originalCoordinate;
        targetPiece = _targetPiece;
        belGame = _belGame;
    }

    public Coordinate getOriginalCoordinate() {
        return originalCoordinate;
    }

    public Piece getTargetPiece() {
        return targetPiece;
    }

    public Game getBelGame() {
        return belGame;
    }

    public abstract void drawBack();

    public abstract void run();

}

class Captured extends Event {
    public Captured(Coordinate _originalCoordinate, Piece _targetPiece, Game _belGame) {
        super(_originalCoordinate, _targetPiece, _belGame);
    }
    public void drawBack() {
        Board curBoard = super.getBelGame().getBoard();
        super.getTargetPiece().setAlive();
        curBoard.getSquare(super.getOriginalCoordinate()).setPiece(super.getTargetPiece());
    }
}

class Move extends Event {
    private final boolean success; // True for success
    private final Coordinate targetCoordinate;
    public Move(Coordinate _originalCoordinate, Piece _targetPiece, Game _belGame, boolean _success, Coordinate _targetCoordinate) {
        super(_originalCoordinate, _targetPiece, _belGame);
        success = _success;
        targetCoordinate = _targetCoordinate;
    }

    public void drawBack() {
        if(!success) {
            throw new IllegalCallerException("You can not drawBack a unsuccessful move");
        }
        Board curBoard = super.getBelGame().getBoard();
        curBoard.getSquare(targetCoordinate).setPiece(null);
        curBoard.getSquare(super.getOriginalCoordinate()).setPiece(super.getTargetPiece());
    }

}

class  extends Event {}