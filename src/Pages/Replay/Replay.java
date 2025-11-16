package Pages.Replay;

import Model.Board;
import Model.Logging.*;
import Pages.Display.Display;

import java.io.Serializable;
import java.util.Scanner;

public class Replay implements Serializable {
    private Board board;
    private String upperPlayerName, lowerPlayerName;
    private static final Scanner scanner = new Scanner(System.in);

    public Replay(Board board, String upperPlayerName, String lowerPlayerName) {
        this.board = board;
        this.upperPlayerName = upperPlayerName;
        this.lowerPlayerName = lowerPlayerName;
    }

    private String getFullName(boolean turn) {
        return turn ? "upperPlayer (" + upperPlayerName + ")" : "lowerPlayer (" + lowerPlayerName + ")";
    }

    public void startReplay() {
        Logger curLogger = board.getLogger();
        curLogger.initReplay();
        boolean currentTurn = false;
        Display.displayBoard(board);
        while(true) {
            System.out.flush();
            System.out.println();
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] arr = line.split("\\s+");
            if(arr.length == 0) {
                continue;
            }
            if(arr.length > 1) {
                System.out.println("Too many arguments, please enter again");
                continue;
            }
            if(arr[0].equalsIgnoreCase("next")) {
                try {
                    System.out.println(getFullName(currentTurn) + "'s turn: ");
                    if(curLogger.nextStep()) {
                        currentTurn = !currentTurn;
                    }
                    Display.displayBoard(board);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if(arr[0].equalsIgnoreCase("prev")) {
                try {
                    if(curLogger.previousStep()) {
                        currentTurn = !currentTurn;
                    }
                    Display.displayBoard(board);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if(arr[0].equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}
