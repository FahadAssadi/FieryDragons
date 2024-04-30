package ui.panels;

import game.chit.DragonCard;
import game.chit.DragonCardManager;
import misc.Settings;
import ui.buttons.DragonCardButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DragonCardPanel extends JPanel {
    private static final int DEFAULT_PANEL_WIDTH = 300;
    private static final int DEFAULT_PANEL_HEIGHT = 300;
    private static final int DEFAULT_BUTTON_GAP = 2;

    public DragonCardPanel(DragonCardManager dragonCardManager, int parentWidth, int parentHeight) {
        super();

        long numberOfDragonCards = (long) Settings.getSetting("DragonCard");
        int gridLength = (int) Math.ceil(Math.sqrt(numberOfDragonCards));

        setBackground(Color.white);
        setPanelBounds(parentWidth, parentHeight);
        setLayout(new GridLayout(gridLength, gridLength, DEFAULT_BUTTON_GAP, DEFAULT_BUTTON_GAP));

        createDragonCards(dragonCardManager);
    }

    private void setPanelBounds(int parentWidth, int parentHeight){
        int x = (parentWidth - DEFAULT_PANEL_WIDTH) / 2;
        int y = (parentHeight - DEFAULT_PANEL_HEIGHT) / 2;

        setBounds(x, y, DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT);
    }

    private void createDragonCards(DragonCardManager dragonCardManager){
        List<DragonCard> dragonCardList = dragonCardManager.getDragonCards();

        // Add images to the buttons
        for (DragonCard dragonCard : dragonCardList) {
            // Create a button with the image
            DragonCardButton dragonCardButton = new DragonCardButton(dragonCardManager, dragonCard);
            this.add(dragonCardButton);
        }
    }

}
