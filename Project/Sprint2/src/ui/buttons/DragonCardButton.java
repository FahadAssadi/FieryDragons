package ui.buttons;

import game.chit.DragonCard;
import game.chit.DragonCardManager;
import game.event.EventManager;
import game.event.EventType;
import misc.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DragonCardButton extends JButton implements ActionListener {
    private final DragonCardManager dragonCardManager;
    private final DragonCard dragonCard;
    private final ImageIcon dragonCardUnflippedImage;
    private final ImageIcon dragonCardFlippedImage;

    private static final String DRAGONCARD_IMG_PATH = "Project/Sprint2/src/resources/assets/pngs/dragoncard/DragonCard.png";
    private static final int BUTTON_WIDTH = 70;
    private static final int BUTTON_HEIGHT = 70;
    private static final int FLIP_DELAY = 500;

    public DragonCardButton(DragonCardManager dragonCardManager, DragonCard dragonCard) {
        super();
        this.dragonCardManager = dragonCardManager;
        this.dragonCard = dragonCard;

        setRolloverEnabled(false);

        ImageIcon dragonCardImage = new ImageIcon(DRAGONCARD_IMG_PATH);
        this.dragonCardUnflippedImage = Utility.getScaledImage(dragonCardImage, BUTTON_WIDTH, BUTTON_HEIGHT);
        setIcon(this.dragonCardUnflippedImage);

        this.dragonCardFlippedImage = Utility.getScaledImage(dragonCard.getDragonCardImage(), BUTTON_WIDTH, BUTTON_HEIGHT);

        addActionListener(this);

        setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    private void flipDragonCardImage(){
        // Set the flipped image
        setIcon(dragonCardFlippedImage);

        // Wait for FLIP_DELAY seconds before unflipping the image
        Timer timer = new Timer(FLIP_DELAY, e -> {
            // Set the unflipped image after the delay
            setIcon(dragonCardUnflippedImage);
        });

        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        flipDragonCardImage();

        this.dragonCardManager.setCurrDragonCard(dragonCard);
        EventManager.getInstance().notify(EventType.PLAYER_TURN_START);
    }
}
