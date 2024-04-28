package ui.buttons;

import game.chit.DragonCard;

import javax.swing.*;
import java.awt.*;

public class DragonCardButton extends JButton {
    private final DragonCard dragonCard;
    private static final String DRAGONCARD_IMG_PATH = "Project/Sprint2/src/resources/assets/pngs/dragoncard/DragonCard.png";

    public DragonCardButton(DragonCard dragonCard) {
        super(new ImageIcon(DRAGONCARD_IMG_PATH));
        this.dragonCard = dragonCard;

        setSize(new Dimension(50, 50));
    }


}
