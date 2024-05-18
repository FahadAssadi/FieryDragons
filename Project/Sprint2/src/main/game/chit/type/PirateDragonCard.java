package main.game.chit.type;

import main.game.command.type.chit.DragonCardCommand;
import main.game.command.type.chit.PirateDragonCardCommand;
import main.game.creature.Creature;

import javax.swing.*;

public class PirateDragonCard extends CharacterDragonCard {

    public PirateDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new PirateDragonCardCommand(this);
    }
}
