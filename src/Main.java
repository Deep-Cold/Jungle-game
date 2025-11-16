import java.util.Scanner;
import Game.Game;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static Game currentGame;
    private static void printWelcome() {
        System.out.println("Welcome to Jungle Game v1.0!");
    }
    public static void main(String[] args) {
        printWelcome();
        while(true) {
            System.out.flush();
            System.out.print("> ");
            String command = sc.nextLine();
            String[] arr = command.split("\\s+");
            if(arr.length == 0) {
                continue;
            }
            switch (arr[0]) {
                case "startNewGame":
                    currentGame = Game.newGame();
                    currentGame.startGame();
                    break;
                case "watchRelay":
                    break;
                case "continueGame":
                    break;
                case "quit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;

            }
        }
    }
}