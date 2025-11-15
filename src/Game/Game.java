package Game;

import Model.Board;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Game implements Serializable {
    private final Board board, originalBoard;
    private final Player upperPlayer, lowerPlayer;
    private boolean currentTure = false; // false means lowerPlayer
    private boolean isActive;
    private final Scanner scanner;

    public Game() {
        board = new Board();
        originalBoard = new Board();
        upperPlayer = new Player();
        lowerPlayer = new Player();
        isActive = true;
        scanner = new Scanner(System.in);
    }

    public void saveGame(String filename) {
        if(!filename.matches("^[a-zA-Z0-9. _-]+$")) {
            throw new IllegalArgumentException("Illegal filename format");
        }
        try {
            FileOutputStream fos = new FileOutputStream(filename + ".jungle");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("File may already exist but can not modify!");
        }
        System.out.println("Save successful!");
    }

    public void closeGame() {
        isActive = false;
        saveReplay(String.valueOf(LocalDateTime.now()).replace(':', '-'));
        saveGame(String.valueOf(LocalDateTime.now()).replace(':', '-'));
    }

    public void saveReplay(String filename) {
        if(!filename.matches("^[a-zA-Z0-9. _-]+$")) {
            throw new IllegalArgumentException("Illegal filename format");
        }
        originalBoard.setLogger(board.getLogger());
        Replay curReplay = new Replay(originalBoard, lowerPlayer.getName(), upperPlayer.getName());
        try {
            FileOutputStream fos = new FileOutputStream(filename + ".replay");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(curReplay);
            out.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("File may already exist but can not modify!");
        }
        System.out.println("Save successful!");
    }

    public void startGame() {
        isActive = true;
        while(isActive) {

        }
    }

    // boolean for whether switching turn
    public boolean commandRunner(String command) {

        return false;
    }

    public Board getBoard() {
        return board;
    }
}
