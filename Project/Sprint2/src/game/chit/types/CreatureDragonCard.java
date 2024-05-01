package game.chit.types;

import game.chit.command.MovePlayerCommand;
import game.chit.command.DragonCardCommand;
import game.creature.Creature;

import javax.swing.*;

/**
 * The CreatureDragonCard class represents a dragon card associated with a specific creature in the game.
 * This type of card allows players to interact with the specified creature.
 */
public class CreatureDragonCard extends CreatureRelatedDragonCard {
    /**
     * Constructs a CreatureDragonCard object with the specified dragon card image, creature, and amount.
     *
     * @param dragonCardImage The image representing the dragon card.
     * @param creature        The creature associated with the dragon card.
     * @param amount          The amount or value associated with the dragon card.
     */
    public CreatureDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    /**
     * Retrieves the command associated with the creature dragon card.
     * In this case, it returns a MovePlayerCommand for moving the player.
     *
     * @return The MovePlayerCommand associated with the creature dragon card.
     */
    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new MovePlayerCommand(this, true);
    }
}
