package Controller;

import Board.Piece;
import Board.Board;

import java.util.Dictionary;
import java.util.Hashtable;

public class MovingValidator {
    private final Dictionary upperPlayerPieces;
    private final Dictionary lowerPlayerPieces;
    private final Board board;

    public MovingValidator(Board _board) {
        board = _board;
        upperPlayerPieces = new Hashtable<String, Piece>();
        lowerPlayerPieces = new Hashtable<String, Piece>();

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

    // For testing only
    public Dictionary getUpperDictionary() {
        return upperPlayerPieces;
    }

    public Dictionary getLowerDictionary() {
        return lowerPlayerPieces;
    }
}
