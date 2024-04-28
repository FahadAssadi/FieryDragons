import game.GameBoard;
import ui.GameUI;

public class Game {
    public static void main(String[] args) {
        GameBoard gB = new GameBoard();
        GameUI ui = new GameUI(gB);

        ui.displayGameUI();
    }

}
