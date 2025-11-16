package Pages.Game;

import Pages.Display.Display;
import Model.Board;
import Pages.Replay.Replay;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Game implements Serializable {
    private final Board board;
    private final Player upperPlayer, lowerPlayer;
    private boolean currentTurn = false; // false means lowerPlayer
    private boolean isActive;

    private static Scanner scanner = new Scanner(System.in);
    private static final String replayPath = "./replay";
    private static final String archivePath = "./archive";

    // For testing only
    public Game() {
        board = new Board();
        upperPlayer = new Player(null);
        lowerPlayer = new Player(null);
        isActive = true;
    }
    
    private Game(String firstName, String secondName) {
        board = new Board();
        upperPlayer = new Player(firstName);
        lowerPlayer = new Player(secondName);
        isActive = true;
    }
    
    public void saveGame(String filename) {
        File dir = new File(archivePath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        if(!filename.matches("^[a-zA-Z0-9. _-]+$")) {
            throw new IllegalArgumentException("Illegal filename format");
        }
        try {
            FileOutputStream fos = new FileOutputStream("./archive/" + filename + ".jungle");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            System.out.println("Saving game to " + "./archive/" + filename + ".jungle");
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
    }
    
    public void saveReplay(String filename) {
        File dir = new File(replayPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        if(!filename.matches("^[a-zA-Z0-9. _-]+$")) {
            throw new IllegalArgumentException("Illegal filename format");
        }
        Replay curReplay = new Replay(board, lowerPlayer.getName(), upperPlayer.getName());
        try {
            FileOutputStream fos = new FileOutputStream("./replay/" + filename + ".replay");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            System.out.println("Saving replay to" + "./replay/" + filename + ".replay");
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
        Display.displayBoardWithLegend(board);
        while(isActive) {
            if(board.checkDen(currentTurn) || !board.haveValidMove(currentTurn)) {
                gameOver(!currentTurn);
                continue;
            }
            System.out.flush();
            System.out.println();
            System.out.print(getFullName(currentTurn) + " > ");
            String line = scanner.nextLine();
            String[] arr = line.split("\\s+");
            if(arr.length == 0) {
                continue;
            }
            if(Utils.checkPieceName(arr[0])) {
                if(arr.length > 2) {
                    System.out.println("Too many argument, please enter again");
                    continue;
                }
                if(!Utils.checkMove(arr[1])) {
                    System.out.println("Illegal move, please enter again");
                    continue;
                }
                if(board.attemptMove(arr[0].toLowerCase(), currentTurn, Character.toUpperCase(arr[1].charAt(0)))) {
                    currentTurn = !currentTurn;
                    Display.displayBoard(board);
                }
            } else if(arr[0].equalsIgnoreCase("withdraw")) {
                if(arr.length > 1) {
                    System.out.println("Too many argument, please enter again");
                }
                if(currentTurn ? upperPlayer.checkWithdrawQuota() : lowerPlayer.checkWithdrawQuota()) {
                    try {
                        board.tryWithdraw(currentTurn);
                        System.out.println("Withdraw successful! You have " + (currentTurn ? upperPlayer.getWithdrawQuota() : lowerPlayer.getWithdrawQuota()) + " withdraw Quota left");
                        Display.displayBoard(board);
                    } catch (IllegalArgumentException e) {
                        System.out.println("You have not made any move!");
                    }
                } else {
                    System.out.println("You do not have enough withdraw quota!");
                }
            } else if(arr[0].equalsIgnoreCase("saveGame")) {
                if(arr.length > 2) {
                    System.out.println("Too many argument, please enter again");
                } else if(arr.length > 1) {
                    try {
                        saveGame(arr[1]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        saveGame(String.valueOf(LocalDateTime.now()).replace(':', '-'));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if(arr[0].equalsIgnoreCase("saveReplay")) {
                if(arr.length > 2) {
                    System.out.println("Too many argument, please enter again");
                } else if(arr.length > 1) {
                    try {
                        saveReplay(arr[1]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        saveReplay(String.valueOf(LocalDateTime.now()).replace(':', '-'));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if(arr[0].equalsIgnoreCase("close")) {
                closeGame();
            } else {
                System.out.println("Unknown command, please enter again");
            }
        }
    }
    
    private String getFullName(boolean turn) {
        return turn ? "upperPlayer (" + upperPlayer.getName() + ")" : "lowerPlayer (" + lowerPlayer.getName() + ")";
    }
    
    private void gameOver(boolean winner) {
        System.out.println(getFullName(winner) + " wins!");
        System.out.println("Game over!");
        isActive = false;
        saveReplay(String.valueOf(LocalDateTime.now()).replace(':', '-'));
    }
    
    public static Game newGame() {
        System.out.print("Please enter the name for the lowerPlayer (empty for a random name) :> ");
        String name1 = scanner.nextLine();
        if(name1.isEmpty()) name1 = null;
        System.out.print("Please enter the name for the upperPlayer (empty for a random name) :> ");
        String name2 = scanner.nextLine();
        if(name2.isEmpty()) name2 = null;
        return new Game(name1, name2);
    }
    
    public Board getBoard() {
        return board;
    }

    // for testing only
    static void setScannerForTesting(Scanner testScanner) {
        scanner = testScanner;
    }
}
