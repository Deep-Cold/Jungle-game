package Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game curGame;

    @BeforeEach
    void setUp() {
        curGame = new Game();
    }

    @Test
    public void testSave() {
        curGame.saveGame("test");
    }

    @Test
    public void testClose() {
        curGame.closeGame();
    }
}