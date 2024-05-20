package main.ui;

import main.game.GameBoard;
import main.ui.frames.GameFrame;

/**
 * The GameUI class manages the user interface of the main.game.
 * It initializes the main.game frame and populates it with panels representing different main.game elements.
 */
public class GameUI implements EventListener {
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

        // Create and add panels representing dragon cards, player turns, and tiles to the game frame
        this.gameFrame.createDragonCardPanel(gameBoard.getDragonCardKeeper());
        this.gameFrame.createPlayerTurnPanel(gameBoard.getTileKeeper());
        this.gameFrame.createTilePanel(gameBoard.getTileKeeper())

        // Subscribe to game over event
        EventManager.getInstance().subscribe(EventType.GAME_OVER, this);
    }

    /**
     * Displays the main.game user interface by setting the main.game frame visible.
     */
    public void displayGameUI(){
        this.gameFrame.setVisible(true);
    }

    /**
     * Updates the game UI in response to game being over.
     * Displays Game Over frame.
     *
     * @param eventType The type of event that occurred.
     */
    @Override
    public void update(EventType eventType) {
        // Get the winner
        String winner = "Placeholder";

        // Hide the main game frame
        gameFrame.setVisible(false);

        // Create and display the Game Over frame
        GameOverFrame gameOverFrame = new GameOverFrame(winner);
        gameOverFrame.setVisible(true);

    }
}
