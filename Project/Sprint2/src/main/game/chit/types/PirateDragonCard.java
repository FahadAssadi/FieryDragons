package main.game.chit.types;

import main.game.chit.command.DragonCardCommand;
import main.game.chit.command.MovePlayerCommand;
import main.game.creature.Creature;

import javax.swing.*;

/**
 * The PirateDragonCard class represents a dragon card associated with a pirate creature in the main.game.
 * This type of card allows players to interact with the specified pirate creature.
 */
public class PirateDragonCard extends CreatureRelatedDragonCard {
    /**
     * Constructs a PirateDragonCard object with the specified dragon card image, creature, and amount.
     *
     * @param dragonCardImage The image representing the dragon card.
     * @param creature        The pirate creature associated with the dragon card.
     * @param amount          The amount or value associated with the dragon card.
     */
    public PirateDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    /**
     * Retrieves the command associated with the pirate dragon card.
     * In this case, it returns a MovePlayerCommand for moving the player in the opposite direction.
     *
     * @return The MovePlayerCommand associated with the pirate dragon card.
     */
    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new MovePlayerCommand(this, false);
    }

}
