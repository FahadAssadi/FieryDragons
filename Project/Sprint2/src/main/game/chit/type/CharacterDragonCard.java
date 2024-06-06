package main.game.chit.type;

import main.game.chit.DragonCard;
import main.game.chit.commands.DragonCardCommand;
import main.game.creature.Creature;

import javax.swing.*;

public abstract class CharacterDragonCard extends DragonCard {
    private final Creature creature;
    private final int amount;

    /**
     * Constructs a DragonCard object with the specified dragon card image.
     *
     * @param dragonCardImage The image representing the dragon card.
     */
    public CharacterDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage);
        this.creature = creature;
        this.amount = amount;
    }

    /**
     * Returns an abstract DragonCardCommand associated with this CharacterDragonCard.
     *
     * @return  an abstract DragonCardCommand
     */
    @Override
    public abstract DragonCardCommand getDragonCardCommand();

    /**
     * Returns the creature associated with this CharacterDragonCard.
     *
     * @return  the creature associated with this CharacterDragonCard
     */
    public Creature getCreature() {
        return creature;
    }

    /**
     * Returns the amount associated with this CharacterDragonCard.
     *
     * @return the amount associated with this CharacterDragonCard
     */
    public int getAmount() {
        return amount;
    }
}
