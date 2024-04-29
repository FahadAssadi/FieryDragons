package ui.buttons;

import game.chit.DragonCard;
import misc.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DragonCardButton extends JButton implements ActionListener {
    private final DragonCard dragonCard;
    private final ImageIcon dragonCardUnflippedImage;
    private final ImageIcon dragonCardFlippedImage;
    private static final String DRAGONCARD_IMG_PATH = "Project/Sprint2/src/resources/assets/pngs/dragoncard/DragonCard.png";
    private static final int BUTTON_WIDTH = 70;
    private static final int BUTTON_HEIGHT = 70;
    private static final int FLIP_DELAY = 2000;

    public DragonCardButton(DragonCard dragonCard) {
        super();
        this.dragonCard = dragonCard;

//        setFocusPainted(false);
//        setRolloverEnabled(false);
//        setBorderPainted(false);

        ImageIcon dragonCardImage = new ImageIcon(DRAGONCARD_IMG_PATH);
        this.dragonCardUnflippedImage = Utility.getScaledImage(dragonCardImage, BUTTON_WIDTH, BUTTON_HEIGHT);
        setIcon(this.dragonCardUnflippedImage);

        this.dragonCardFlippedImage = Utility.getScaledImage(dragonCard.getDragonCardImage(), BUTTON_WIDTH, BUTTON_HEIGHT);

        addActionListener(this);

        setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This method will be called when the button is clicked
        System.out.println("Clicked Dragon Card: " + dragonCard);
        // You can add more code here to perform other actions with the DragonCard
    }
}
