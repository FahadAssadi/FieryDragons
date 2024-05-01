package main.ui;

import main.game.GameBoard;
import main.ui.frames.GameFrame;

/**
 * The GameUI class manages the user interface of the main.game.
 * It initializes the main.game frame and populates it with panels representing different main.game elements.
 */
public class GameUI {
    // The main.game frame containing the user interface components
    private final GameFrame gameFrame;

    /**
     * Constructs a GameUI object with the specified main.game board.
     * Initializes the main.game frame and populates it with panels representing main.game elements.
     *
     * @param gameBoard The main.game board containing main.game-related information.
     */
    public GameUI(GameBoard gameBoard) {
        this.gameFrame = new GameFrame();

        // Create and add panels representing dragon cards, player turns, and tiles to the main.game frame
        this.gameFrame.createDragonCardPanel(gameBoard.getDragonCardManager());
        // Panel created for main.testing purposes.
        this.gameFrame.createPlayerTurnPanel(gameBoard.getPlayerManager());
        this.gameFrame.createTilePanel(gameBoard.getTileManager());
    }

    /**
     * Displays the main.game user interface by setting the main.game frame visible.
     */
    public void displayGameUI(){
        this.gameFrame.setVisible(true);
    }
}
