package main;

import main.game.GameBoard;
import main.ui.GameUI;

/**
 * The main.Game class serves as the entry point for the application.
 * It initializes the game board and the user interface and displays the game UI.
 */
public class Game {
    public static void main(String[] args) {
        // Create the main.game board and the main.ui
        GameBoard gB = new GameBoard();
        GameUI ui = new GameUI(gB);

        // Display the main.ui
        ui.displayGameUI();
    }

}
