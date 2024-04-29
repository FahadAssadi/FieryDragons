package game.chit.types;

import game.chit.command.DragonCardCommand;
import game.chit.command.MovePlayerCommand;
import game.creature.Creature;

import javax.swing.*;

public class PirateDragonCard extends CreatureRelatedDragonCard {
    public PirateDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new MovePlayerCommand(this, false);
    }

}
