package main.game.chit.type;

import main.game.command.type.chit.CreatureDragonCardCommand;
import main.game.command.type.chit.DragonCardCommand;
import main.game.creature.Creature;

import javax.swing.*;

public class CreatureDragonCard extends CharacterDragonCard{
    public CreatureDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new CreatureDragonCardCommand(this);
    }
}
