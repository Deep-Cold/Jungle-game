package Game;

import Model.Board;

import java.io.Serializable;

public class Replay implements Serializable {
    private Board board;
    private String upperPlayerName, lowerPlayerName;

    public Replay(Board board, String upperPlayerName, String lowerPlayerName) {
        this.board = board;
        this.upperPlayerName = upperPlayerName;
        this.lowerPlayerName = lowerPlayerName;
    }

    public void startReplay() {

    }
}
