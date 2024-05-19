package main.game.chit;

import main.game.chit.commands.DragonCardCommand;

import javax.swing.*;

/**
 * The DragonCard class represents a card with a dragon image in the main.game.
 * This class is abstract and provides a blueprint for specific types of dragon cards.
 */
public abstract class DragonCard {
    // The image representing the dragon card
    private final ImageIcon dragonCardImage;

    /**
     * Constructs a DragonCard object with the specified dragon card image.
     *
     * @param dragonCardImage The image representing the dragon card.
     */
    public DragonCard(ImageIcon dragonCardImage) {
        this.dragonCardImage = dragonCardImage;
    }

    /**
     * Abstract method to get the command associated with the dragon card.
     *
     * @return The DragonCardCommand associated with the dragon card.
     */
    public abstract DragonCardCommand getDragonCardCommand();

    /**
     * Retrieves the image representing the dragon card.
     *
     * @return The ImageIcon representing the dragon card image.
     */
    public ImageIcon getDragonCardImage() {
        return dragonCardImage;
    }
}
