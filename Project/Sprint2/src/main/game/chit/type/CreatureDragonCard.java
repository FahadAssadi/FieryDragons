package main.game.chit.type;

import main.game.chit.commands.CreatureDragonCardCommand;
import main.game.chit.commands.DragonCardCommand;
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
