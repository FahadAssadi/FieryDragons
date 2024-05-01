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

/**
 * The DragonCardButton class represents a button for displaying a dragon card in the UI.
 * It extends the JButton class and implements the ActionListener interface.
 */
public class DragonCardButton extends JButton implements ActionListener {
    private final DragonCardManager dragonCardManager;
    private final DragonCard dragonCard;
    private final ImageIcon dragonCardUnflippedImage;
    private final ImageIcon dragonCardFlippedImage;

    private static final String DRAGONCARD_IMG_PATH = "Project/Sprint2/src/resources/assets/pngs/dragoncard/DragonCard.png";
    private static final int BUTTON_WIDTH = 90;
    private static final int BUTTON_HEIGHT = 90;
    private static final int FLIP_DELAY = 500;

    /**
     * Constructs a DragonCardButton object with the specified dragon card manager and dragon card.
     *
     * @param dragonCardManager The dragon card manager.
     * @param dragonCard        The dragon card associated with the button.
     */
    public DragonCardButton(DragonCardManager dragonCardManager, DragonCard dragonCard) {
        super();
        this.dragonCardManager = dragonCardManager;
        this.dragonCard = dragonCard;

        setRolloverEnabled(false);

        // Set the unflipped dragon card image
        ImageIcon dragonCardImage = new ImageIcon(DRAGONCARD_IMG_PATH);
        this.dragonCardUnflippedImage = Utility.getScaledImage(dragonCardImage, BUTTON_WIDTH, BUTTON_HEIGHT);
        setIcon(this.dragonCardUnflippedImage);

        // Set the flipped dragon card image
        this.dragonCardFlippedImage = Utility.getScaledImage(dragonCard.getDragonCardImage(), BUTTON_WIDTH, BUTTON_HEIGHT);

        addActionListener(this);

        setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    /**
     * Flips the dragon card image when the button is clicked.
     */
    private void flipDragonCardImage() {
        // Set the flipped image
        setIcon(dragonCardFlippedImage);

        // Wait for FLIP_DELAY milliseconds before unflipping the image
        Timer timer = new Timer(FLIP_DELAY, e -> {
            // Set the unflipped image after the delay
            setIcon(dragonCardUnflippedImage);
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Handles button click events.
     *
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        flipDragonCardImage();

        // Set the current dragon card and notify the event manager about the player's turn start
        this.dragonCardManager.setCurrDragonCard(dragonCard);
        EventManager.getInstance().notify(EventType.PLAYER_TURN_START);
    }
}
