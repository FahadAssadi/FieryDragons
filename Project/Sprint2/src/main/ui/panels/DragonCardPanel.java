package main.ui.panels;

import main.game.GameBoard;
import main.game.chit.DragonCard;
import main.game.chit.iterators.DragonCardIterable;
import main.game.chit.DragonCardKeeper;
import main.misc.Settings;
import main.ui.buttons.DragonCardButton;

import javax.swing.*;
import java.awt.*;

/**
 * The DragonCardPanel class represents a panel displaying dragon cards in the game UI.
 * It extends the JPanel class and arranges dragon card buttons in a grid layout.
 */
public class DragonCardPanel extends JPanel {
    // Default panel dimensions and button gap
    private static final int DEFAULT_PANEL_WIDTH = 300;
    private static final int DEFAULT_PANEL_HEIGHT = 300;
    private static final int DEFAULT_BUTTON_GAP = 2;


    public DragonCardPanel(GameBoard gameBoard, int parentWidth, int parentHeight) {
        super();

        // Calculate the grid dimensions based on the number of dragon cards
        long numberOfDragonCards = (long) Settings.getSetting("DragonCard");
        int gridLength = (int) Math.ceil(Math.sqrt(numberOfDragonCards));

        // Set panel background color and layout
        setBackground(Color.white);
        setPanelBounds(parentWidth, parentHeight);
        setLayout(new GridLayout(gridLength, gridLength, DEFAULT_BUTTON_GAP, DEFAULT_BUTTON_GAP));

        // Create dragon card buttons
        createDragonCards(gameBoard);
    }

    /**
     * Sets the bounds of the panel within its parent container.
     *
     * @param parentWidth  The width of the parent container.
     * @param parentHeight The height of the parent container.
     */
    private void setPanelBounds(int parentWidth, int parentHeight) {
        int x = (parentWidth - DEFAULT_PANEL_WIDTH) / 2;
        int y = (parentHeight - DEFAULT_PANEL_HEIGHT) / 2;

        setBounds(x, y, DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT);
    }

    /**
     * Creates dragon card buttons based on the dragon cards in the given game board.
     *
     * @param  gameBoard  the game board containing the dragon cards
     */
    private void createDragonCards(GameBoard gameBoard) {
        DragonCardIterable dragonCardIterable = gameBoard.getDragonCardKeeper().getDragonCardIterable();

        // Add images to the buttons
        for (DragonCard dragonCard : dragonCardIterable) {
            // Create a button with the image
            DragonCardButton dragonCardButton = new DragonCardButton(gameBoard, dragonCard);
            this.add(dragonCardButton);
        }
    }
}
