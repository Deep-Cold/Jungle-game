package Logging;

import Board.Piece;
import Board.Board;
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
}

class Captured extends Event {
    public Captured(Coordinate _originalCoordinate, Piece _targetPiece, Game _belGame) {
        super(_originalCoordinate, _targetPiece, _belGame);
    }
    public void drawBack() {
        Board curBoard = super.getBelGame().getBoard();
        super.getTargetPiece().setAlive(curBoard.getSquare(super.getOriginalCoordinate()));
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

    }
}