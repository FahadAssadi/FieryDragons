package main.ui;

import main.game.GameBoard;
import main.ui.frames.GameFrame;

/**
 * The GameUI class manages the user interface of the main.game.
 * It initializes the game frame and populates it with panels representing different main.game elements.
 */
public class GameUI {
    // The main.game frame containing the user interface components
    private final GameFrame gameFrame;

    /**
     * Constructs a GameUI object with the specified game board.
     * Initializes the game frame and populates it with panels representing game elements.
     *
     * @param gameBoard The game board containing game-related information.
     */
    public GameUI(GameBoard gameBoard) {
        this.gameFrame = new GameFrame();

        // Create and add panels representing dragon cards, player turns, and tiles to the game frame
//        this.gameFrame.createDragonCardPanel(gameBoard.getDragonCardManager());
        // Panel created for testing purposes.
        this.gameFrame.createPlayerTurnPanel(gameBoard.getTileKeeper());
        this.gameFrame.createTilePanel(gameBoard.getTileKeeper());
    }

    /**
     * Displays the game user interface by setting the game frame visible.
     */
    public void displayGameUI(){
        this.gameFrame.setVisible(true);
    }
}
