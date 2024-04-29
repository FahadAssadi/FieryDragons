package game.chit.types;

import game.chit.command.MovePlayerCommand;
import game.chit.command.DragonCardCommand;
import game.creature.Creature;

import javax.swing.*;

public class CreatureDragonCard extends CreatureRelatedDragonCard {

    public CreatureDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new MovePlayerCommand(this, true);
    }
}
