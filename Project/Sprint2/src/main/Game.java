package main;

import main.game.GameBoard;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.misc.Settings;
import main.misc.Utility;
import main.ui.GameUI;

import java.io.InputStream;

/**
 * The main.Game class serves as the entry point for the application.
 * It initializes the game board and the user interface and displays the game UI.
 */
public class Game implements EventListener {
    private GameBoard gameBoard;
    private GameUI gameUI;

    public Game() {
        // Create the main.game board and the main.ui
        this.gameBoard = new GameBoard();
        this.gameUI = new GameUI(this.gameBoard);

        EventManager.getInstance().subscribe(EventType.SAVE, this);
        EventManager.getInstance().subscribe(EventType.LOAD, this);
    }

    public void startSaveProcess() {
        Settings.getInstance().incrementSaveCount();
        long saveNumber = (long) Settings.getSetting("Saves");

        String path = "save_" + saveNumber;

        this.gameBoard.save(path);
    }

    public void startLoadProcess() {
        String path = "save_";

        this.gameBoard.load(path);
    }

    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.SAVE) {
            startSaveProcess();
        } else if (eventType == EventType.LOAD) {
            startLoadProcess();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        // Display the main.ui
        game.gameUI.displayGameUI();
    }
}