package game.chit.types;

import game.chit.DragonCard;
import game.chit.DragonCardCommand;
import game.creature.Creature;

import javax.swing.*;

public class CreatureDragonCard extends DragonCard {
    private final Creature creature;
    private final int amount;

    public CreatureDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage);
        this.creature = creature;
        this.amount = amount;
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return null;
    }
}
