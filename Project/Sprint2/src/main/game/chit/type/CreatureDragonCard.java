package main.game.chit.type;

import main.game.chit.commands.CreatureDragonCardCommand;
import main.game.chit.commands.DragonCardCommand;
import main.game.creature.Creature;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CreatureDragonCard extends CharacterDragonCard{
    public CreatureDragonCard(ImageIcon dragonCardImage, Creature creature, int amount) {
        super(dragonCardImage, creature, amount);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new CreatureDragonCardCommand(this);
    }

    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        Map<String , Object> creatureDragonCardMap = new HashMap<>();

        creatureDragonCardMap.put("creature", this.getCreature().getCreatureName());
        creatureDragonCardMap.put("amount", this.getAmount());
        return creatureDragonCardMap;
    }

    @Override
    public Map<String , Object> load(Map<String , Object> map) {
        return Map.of();
    }
}
