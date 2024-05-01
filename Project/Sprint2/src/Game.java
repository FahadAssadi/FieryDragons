import game.GameBoard;
import ui.GameUI;

/**
 * The Game class serves as the entry point for the application.
 * It initializes the game board and the user interface and displays the game UI.
 */
public class Game {
    public static void main(String[] args) {
        // Create the game board and the ui
        GameBoard gB = new GameBoard();
        GameUI ui = new GameUI(gB);

        // Display the ui
        ui.displayGameUI();
    }

}
