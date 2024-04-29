package game.chit.types;

import game.chit.DragonCard;
import game.creature.Creature;

import javax.swing.*;

public abstract class CreatureRelatedDragonCard extends DragonCard {
    protected final Creature creature;
    protected final int amount;

    public CreatureRelatedDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage);
        this.creature = creature;
        this.amount = amount;
    }

    public Creature getCreature() {
        return creature;
    }

    public int getAmount() {
        return amount;
    }
}
