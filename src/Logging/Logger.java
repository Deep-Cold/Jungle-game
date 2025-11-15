package Logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Logger implements Serializable {
    private final Stack<Event> curStack;
    private final ArrayList<Event> allEvents;
    public Logger() {
        curStack = new Stack<>();
        allEvents = new ArrayList<>();
    }

    

    // For debug only

}
