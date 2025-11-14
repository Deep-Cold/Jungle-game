package Controller;

import Board.*;
import Elements.*;

import java.util.HashMap;

public class MovingValidator {
    private final HashMap<String, Piece> upperPlayerPieces;
    private final HashMap<String, Piece> lowerPlayerPieces;
    private final Board board;

    public MovingValidator(Board _board) {
        board = _board;
        upperPlayerPieces = new HashMap<String, Piece>();
        lowerPlayerPieces = new HashMap<String, Piece>();

        int n = board.getLength(), m = board.getWidth();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                Piece curPiece = board.getPiece(i, j);
                if(curPiece == null) {
                    continue;
                }
                if(!curPiece.getBelongs()) {
                    lowerPlayerPieces.put(curPiece.getType().toString(), curPiece);
                } else {
                    upperPlayerPieces.put(curPiece.getType().toString(), curPiece);
                }
            }
        }
    }

    public boolean temptCapture(Piece attacker, Piece defender) {
        if(attacker.getBelongs() == defender.getBelongs()) {
            return false;
        }
        if(((attacker.getPosition().getType() == SquareType.River) == (defender.getPosition().getType() == SquareType.River))) {
            if(defender.getPosition().getType() == SquareType.Trap) {
                return true;
            } else {
                return attacker.getType().canCapture(defender.getType());
            }
        }
        return false;
    }

    /*
        turn should be 0 if this is the lower player's turn
     */
    public Piece getTargetPiece(String name, boolean side) {
        return (Piece) (side ? upperPlayerPieces.get(name) : lowerPlayerPieces.get(name));
    }

    /*
        turn should be 0 if this is the lower player's turn
     */
    public Coordinate temptMove(String name, boolean turn, char direction) {
        Piece curPiece = (Piece) (turn ? upperPlayerPieces.get(name) : lowerPlayerPieces.get(name));

        if(!curPiece.getStatus()) {
            throw new IllegalArgumentException("You can not a piece that has been captured");
        }

        Coordinate newPosition = new Coordinate(curPiece.getPosition().getCoordinate());
        newPosition.moveByChar(direction);

        if(!newPosition.checkBound(board.getLength(), board.getWidth())) {
            throw new IllegalArgumentException("Your move out of the board!");
        }

        Square curSquare = board.getSquare(newPosition);

        if(curSquare.getType() == SquareType.Den && ((SpecialSquare) curSquare).getSide() == curPiece.getBelongs()) {
            throw new IllegalArgumentException("You can not move in your den");
        }

        if(curSquare.getType() == SquareType.River) {
            if(curPiece.getType() == PieceType.Rat) {
                if(curSquare.getPiece() == null) {
                    return newPosition;
                }
                if(temptCapture(curPiece, curSquare.getPiece())) {
                    return newPosition;
                } else {
                    if(curPiece.getBelongs() == curSquare.getPiece().getBelongs()) {
                        throw new IllegalArgumentException("The target square have another your piece");
                    }
                    throw new IllegalArgumentException("You can not capture target piece!");
                }

            } else if(curPiece.getType() == PieceType.Lion || curPiece.getType() == PieceType.Tiger) {
                while(newPosition.checkBound(board.getLength(), board.getWidth())) {
                    curSquare = board.getSquare(newPosition);
                    if(curSquare.getType() == SquareType.River) {
                        if(curSquare.getPiece() != null) {
                            throw new IllegalArgumentException("You can not cross a river with another piece in the middle");
                        }
                    } else {
                        if(curSquare.getPiece() != null) {
                            if(temptCapture(curPiece, curSquare.getPiece())) {
                                return newPosition;
                            } else {
                                if(curPiece.getBelongs() == curSquare.getPiece().getBelongs()) {
                                    throw new IllegalArgumentException("The target square have another your piece");
                                }
                                throw new IllegalArgumentException("You can not capture target piece!");
                            }
                        }
                    }
                    newPosition.moveByChar(direction);
                }
            } else {
                throw new IllegalArgumentException("You can not place move this piece to a river matrix!");
            }
        } else {
            if(curSquare.getPiece() == null) {
                return newPosition;
            }
            if(temptCapture(curPiece, curSquare.getPiece())) {
                return newPosition;
            } else {
                if(curPiece.getBelongs() == curSquare.getPiece().getBelongs()) {
                    throw new IllegalArgumentException("The target square have another your piece");
                }
                throw new IllegalArgumentException("You can not capture target piece!");
            }
        }

        return null;
    }

    // For testing only
    public HashMap<String, Piece> getUpperDictionary() {
        return upperPlayerPieces;
    }

    public HashMap<String, Piece> getLowerDictionary() {
        return lowerPlayerPieces;
    }

}
