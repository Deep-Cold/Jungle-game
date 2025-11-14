package Game;

import Board.Board;

public class Game {
    private final Board board;
    private final Player upperPlayer, lowerPlayer;

    public Game() {
        board = new Board();
        upperPlayer = new Player();
        lowerPlayer = new Player();
    }

    public void save() {

    }

    public Board getBoard() {
        return board;
    }
}
