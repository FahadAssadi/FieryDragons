package main;

import main.game.GameBoard;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.misc.Utility;
import main.ui.GameUI;
import main.ui.frames.LoadAndSave;

import java.util.LinkedHashMap;
import java.util.Map;

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

    /**
     * Starts the save process by saving the current game state to a YAML file.
     *
     * @return  void
     */
    public void startSaveProcess() {
        new LoadAndSave().save(this.gameBoard);
    }

    /**
     * Starts the load process by loading the game state from a YAML file.
     *
     * @return  void
     */
    public void startLoadProcess() {
        this.gameBoard = new LoadAndSave().load();
    }

    /**
     * Updates the state of the object based on the given event type.
     *
     * @param  eventType  the type of event that occurred
     */
    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.SAVE) {
            startSaveProcess();
        } else if (eventType == EventType.LOAD) {
            startLoadProcess();
        }
    }

    /**
     * The main function of the Java program.
     *
     * @param  args  the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.gameUI.displayStartGameUI();
    }
}