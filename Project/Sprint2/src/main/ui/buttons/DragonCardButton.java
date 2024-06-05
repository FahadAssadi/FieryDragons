package main.ui.buttons;

import main.game.GameBoard;
import main.game.chit.DragonCard;
import main.misc.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DragonCardButton class represents a button for displaying a dragon card in the UI.
 * It extends the JButton class and implements the ActionListener interface.
 */
public class DragonCardButton extends JButton implements ActionListener {
    private final GameBoard gameBoard;
    private final DragonCard dragonCard;
    private final ImageIcon dragonCardUnflippedImage;
    private final ImageIcon dragonCardFlippedImage;

    private static final String DRAGONCARD_IMG_PATH = "/assets/pngs/dragoncard/DragonCard.png";
    private static final int BUTTON_WIDTH = 90;
    private static final int BUTTON_HEIGHT = 90;
    private static final int FLIP_DELAY = 500;

    public DragonCardButton(GameBoard gameBoard, DragonCard dragonCard) {
        super();
        this.gameBoard = gameBoard;
        this.dragonCard = dragonCard;

        setRolloverEnabled(false);

        // Set the unflipped dragon card image
        ImageIcon dragonCardImage = new ImageIcon(getClass().getResource(DRAGONCARD_IMG_PATH));
        this.dragonCardUnflippedImage = Utility.getScaledImage(dragonCardImage, BUTTON_WIDTH, BUTTON_HEIGHT);

        // Set the flipped dragon card image
        this.dragonCardFlippedImage = Utility.getScaledImage(dragonCard.getDragonCardImage(), BUTTON_WIDTH, BUTTON_HEIGHT);

        setIcon(this.dragonCardUnflippedImage);

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

        // Execute the dragon card command
        this.dragonCard.getDragonCardCommand().execute(gameBoard);
    }
}
