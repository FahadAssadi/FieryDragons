package main.ui;

import main.game.GameBoard;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.ui.frames.GameFrame;
import main.ui.frames.GameOverFrame;

/**
 * The GameUI class manages the user interface of the main.game.
 * It initializes the game frame and populates it with panels representing different main.game elements.
 */
public class GameUI implements EventListener {
    // The main.game frame containing the user interface components
    private final GameFrame gameFrame;
    private final GameBoard gameBoard;

    /**
     * Constructs a GameUI object with the specified game board.
     * Initializes the game frame and populates it with panels representing game elements.
     *
     * @param gameBoard The game board containing game-related information.
     */
    public GameUI(GameBoard gameBoard) {
        this.gameFrame = new GameFrame();
        this.gameBoard = gameBoard;

        // Create and add panels representing dragon cards, player turns, and tiles to the game frame
        this.gameFrame.createDragonCardPanel(gameBoard);
        this.gameFrame.createPlayerTurnPanel(gameBoard.getTileKeeper());
        this.gameFrame.createTilePanel(gameBoard.getTileKeeper());

        // Subscribe to game over event
        EventManager.getInstance().subscribe(EventType.GAME_OVER, this);
    }

    /**
     * Displays the game user interface by setting the game frame visible.
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
        // Get the winning player
        Player winningPlayer = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode().getType().getPlayer();

        System.out.println(winningPlayer);

        // Create and display the Game Over frame
        GameOverFrame gameOverFrame = new GameOverFrame(winningPlayer);
        gameOverFrame.setVisible(true);
    }

    /**
     * Returns the game frame containing the user interface components.
     *
     * @return the game frame
     */
    public GameFrame getGameFrame() {
        return gameFrame;
    }
}
