package ui;

import game.GameBoard;
import ui.frames.GameFrame;

public class GameUI {
    private GameFrame gameFrame;

    public GameUI(GameBoard gameBoard) {
        this.gameFrame = new GameFrame();
        this.gameFrame.createDragonCardPanel(gameBoard);

    }

    public void displayGameUI(){
        this.gameFrame.setVisible(true);
    }
}
