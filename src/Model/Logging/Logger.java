package Model.Logging;

import Model.Elements.Coordinate;
import Model.Board;
import Model.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Logger implements Serializable {
    private final Stack<MoveEvent> curStack;
    private final ArrayList<Event> allEvents;
    private int moveCounter;
    public Logger() {
        curStack = new Stack<>();
        allEvents = new ArrayList<>();
        moveCounter = 0;
    }


    private void addEvent(Event event) {
        allEvents.add(event);
        if(event instanceof MoveEvent) {
            curStack.push((MoveEvent) event);
        }
        if(event instanceof Move) {
            moveCounter++;
        }
    }

    public void addCaptured(Coordinate _originalCoordinate, Piece _targetPiece, Board _belBoard) {
        Captured curEvent = new Captured(_originalCoordinate, _targetPiece, _belBoard);
        addEvent(curEvent);
        curEvent.printMessage();
    }

    public void addMove(Coordinate _originalCoordinate, Piece _targetPiece, Board _belBoard, boolean _success, Coordinate _targetCoordinate) {
        Move moveEvent = new Move(_originalCoordinate, _targetPiece, _belBoard, _success, _targetCoordinate);
        addEvent(moveEvent);
        moveEvent.printMessage();
    }

    public void tryWithdraw(boolean turn) {
        if(moveCounter < 2) {
            throw new IllegalArgumentException("You did not made any move");
        }
        int rem = 2;
        while(rem > 0) {
            MoveEvent currentEvent = curStack.pop();
            if(currentEvent instanceof Move) {
                moveCounter--;
                rem--;
            }
            currentEvent.withdraw();
            currentEvent.printReverseMessage();
        }
        Withdraw withdrawEvent = new Withdraw(turn);
        addEvent(withdrawEvent);
        withdrawEvent.printMessage();
    }
}
