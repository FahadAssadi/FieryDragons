package main.ui;

import java.util.List;
import java.util.ArrayList;
import main.game.GameBoard;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.ui.frames.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * Displays the start game user interface by creating and showing the start game frame.
     */
    public void displayStartGameUI() {
        StartGameFrame startGameFrame = new StartGameFrame(new StartNewGameListener(), new LoadGameListener());
        startGameFrame.setVisible(true);
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
        // Get number of players
        int numPlayers = gameBoard.getTileKeeper().getPlayerTileQueue().size();

        // Get list of players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            Player player = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode().getType().getPlayer();
            gameBoard.getTileKeeper().getPlayerTileQueue().queueNextTurn();
            players.add(player);
        }

        // Create and display the Game Over frame
        GameOverFrame gameOverFrame = new GameOverFrame(players);
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

    private class StartNewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayGameUI();
        }
    }

    private class LoadGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoadGameFrame loadGameFrame = new LoadGameFrame(new LoadGame1Listener(), new LoadGame2Listener(), new LoadGame3Listener());
            loadGameFrame.setVisible(true);
        }
    }

    private class LoadGame1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Load game 1 functionality to be implemented
            LoadAndSave.getInstance().setLoadFileNumber(1);

            EventManager.getInstance().notify(EventType.LOAD);
        }
    }

    private class LoadGame2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoadAndSave.getInstance().setLoadFileNumber(2);

            // Load game 2 functionality to be implemented
            System.out.println("Load Game 2 button clicked!");
            EventManager.getInstance().notify(EventType.LOAD);
        }
    }

    private class LoadGame3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoadAndSave.getInstance().setLoadFileNumber(3);
            // Load game 3 functionality to be implemented
            EventManager.getInstance().notify(EventType.LOAD);
            System.out.println("Load Game 3 button clicked!");
        }
    }
}
