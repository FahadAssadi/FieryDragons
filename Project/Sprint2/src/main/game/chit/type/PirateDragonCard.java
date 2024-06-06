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

    /**
     * Returns a DragonCardCommand associated with this PirateDragonCard.
     *
     * @return  a DragonCardCommand
     */
    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new PirateDragonCardCommand(this);
    }

    /**
     * Saves the data related to the PirateDragonCard into a Map.
     *
     * @param  map  the Map to save data into
     * @return      the updated Map containing saved data
     */
    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("hasCreature", true);
        map.put("creature", this.getCreature().getCreatureID());
        map.put("amount", this.getAmount());
        return map;
    }

}
