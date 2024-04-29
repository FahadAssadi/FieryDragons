package ui.panels;

import game.chit.DragonCard;
import game.chit.DragonCardManager;
import ui.buttons.DragonCardButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DragonCardPanel extends JPanel {
    private static final int DEFAULT_PANEL_WIDTH = 250;
    private static final int DEFAULT_PANEL_HEIGHT = 250;
    private static final int GRID_ROWS = 4;
    private static final int GRID_COLUMNS = 4;

    public DragonCardPanel(DragonCardManager dragonCardManager, int parentWidth, int parentHeight) {
        super();

        setBackground(Color.white);
        setPanelBounds(parentWidth, parentHeight);
        setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS, 5, 5));
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
