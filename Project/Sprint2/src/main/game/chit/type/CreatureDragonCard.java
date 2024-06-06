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

    /**
     * Returns a new instance of DragonCardCommand, specifically a CreatureDragonCardCommand,
     * which is associated with this CreatureDragonCard.
     *
     * @return  a new instance of DragonCardCommand
     */
    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new CreatureDragonCardCommand(this);
    }

    /**
     * Saves the current state of the CreatureDragonCard object to a map.
     *
     * @param  map  the map to which the state of the object will be saved
     * @return      a map containing the creature ID and the amount of the CreatureDragonCard
     */
    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("hasCreature", true);
        map.put("creature", this.getCreature().getCreatureID());
        map.put("amount", this.getAmount());
        return map;
    }
}
