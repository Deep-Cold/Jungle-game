package Game;

import Model.Board;

import java.io.Serializable;

public class Game implements Serializable {
    private final Board board, originalBoard;
    private final Player upperPlayer, lowerPlayer;
    private boolean currentTure = false; // false means lowerPlayer

    public Game() {
        board = new Board();
        originalBoard = new Board();
        upperPlayer = new Player();
        lowerPlayer = new Player();
    }

    public void saveGame() {

    }

    public void saveReplay() {

    }

    public Board getBoard() {
        return board;
    }
}
