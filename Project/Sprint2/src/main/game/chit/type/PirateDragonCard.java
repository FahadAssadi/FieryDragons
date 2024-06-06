package main.game.chit.type;

import main.game.chit.commands.DragonCardCommand;
import main.game.chit.commands.PirateDragonCardCommand;
import main.game.creature.Creature;

import javax.swing.*;
import java.util.Map;

public class PirateDragonCard extends CharacterDragonCard {

    public PirateDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new PirateDragonCardCommand(this);
    }


    @Override
    public Map save(Map map) {
        return Map.of();
    }

    @Override
    public Map load(Map map) {
        return Map.of();
    }
}
