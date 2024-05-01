package game.chit.types;

import game.chit.DragonCard;
import game.creature.Creature;

import javax.swing.*;

/**
 * The CreatureRelatedDragonCard class represents a dragon card associated with a specific creature in the game.
 * This abstract class provides a blueprint for specific types of dragon cards related to creatures.
 */
public abstract class CreatureRelatedDragonCard extends DragonCard {
    // The creature associated with the dragon card
    protected final Creature creature;
    // The amount or value associated with the dragon card
    protected final int amount;

    /**
     * Constructs a CreatureRelatedDragonCard object with the specified dragon card image, creature, and amount.
     *
     * @param dragonCardImage The image representing the dragon card.
     * @param creature        The creature associated with the dragon card.
     * @param amount          The amount or value associated with the dragon card.
     */
    public CreatureRelatedDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage);
        this.creature = creature;
        this.amount = amount;
    }

    /**
     * Retrieves the creature associated with the dragon card.
     *
     * @return The Creature object associated with the dragon card.
     */
    public Creature getCreature() {
        return creature;
    }

    /**
     * Retrieves the amount or value associated with the dragon card.
     *
     * @return The amount or value associated with the dragon card.
     */
    public int getAmount() {
        return amount;
    }
}
