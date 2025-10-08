package Game;

public class Player {
    private final String name;
    private int numOfRemainPieces;

    public Player(String _name, int _numOfRemainPieces) {
        name = _name;
        numOfRemainPieces = _numOfRemainPieces;
    }

    public String getName() {
        return name;
    }

    public void deductPiece() {
        numOfRemainPieces--;
    }

    public boolean checkRemainPieces() {
        return numOfRemainPieces > 0;
    }
}
