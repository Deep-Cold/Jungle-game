package Board;

import Elements.PieceType;
import Elements.SquareType;

public class Piece {
    private final PieceType type;
    private Square curPosition;
    private boolean status; // 0 for died, 1 for alive

    public Piece(PieceType _type) {
        type = _type;
        status = true;
    }

    public boolean getStatus() {
        return status;
    }

    public PieceType getType() {
        return type;
    }
}
