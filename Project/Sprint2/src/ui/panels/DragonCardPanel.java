package ui.panels;

import game.chit.DragonCard;
import game.chit.DragonCardManager;
import misc.Settings;
import ui.buttons.DragonCardButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The DragonCardPanel class represents a panel displaying dragon cards in the game UI.
 * It extends the JPanel class and arranges dragon card buttons in a grid layout.
 */
public class DragonCardPanel extends JPanel {
    // Default panel dimensions and button gap
    private static final int DEFAULT_PANEL_WIDTH = 300;
    private static final int DEFAULT_PANEL_HEIGHT = 300;
    private static final int DEFAULT_BUTTON_GAP = 2;

    /**
     * Constructs a DragonCardPanel object with the specified dragon card manager, parent width, and parent height.
     * Initializes the panel's layout and creates dragon card buttons.
     *
     * @param dragonCardManager The dragon card manager containing dragon card data.
     * @param parentWidth       The width of the parent container.
     * @param parentHeight      The height of the parent container.
     */
    public DragonCardPanel(DragonCardManager dragonCardManager, int parentWidth, int parentHeight) {
        super();

        // Calculate the grid dimensions based on the number of dragon cards
        long numberOfDragonCards = (long) Settings.getSetting("DragonCard");
        int gridLength = (int) Math.ceil(Math.sqrt(numberOfDragonCards));

        // Set panel background color and layout
        setBackground(Color.white);
        setPanelBounds(parentWidth, parentHeight);
        setLayout(new GridLayout(gridLength, gridLength, DEFAULT_BUTTON_GAP, DEFAULT_BUTTON_GAP));

        // Create dragon card buttons
        createDragonCards(dragonCardManager);
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
     * Creates dragon card buttons and adds them to the panel.
     *
     * @param dragonCardManager The dragon card manager containing dragon card data.
     */
    private void createDragonCards(DragonCardManager dragonCardManager) {
        List<DragonCard> dragonCardList = dragonCardManager.getDragonCards();

        // Add images to the buttons
        for (DragonCard dragonCard : dragonCardList) {
            // Create a button with the image
            DragonCardButton dragonCardButton = new DragonCardButton(dragonCardManager, dragonCard);
            this.add(dragonCardButton);
        }
    }
}
