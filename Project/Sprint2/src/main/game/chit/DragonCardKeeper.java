package main.game.chit;

import main.game.chit.iterators.DragonCardIterable;
import main.game.creature.iterators.CreatureIterable;
import main.game.snapshot.Memento;

import java.util.HashMap;
import java.util.Map;

public class DragonCardKeeper implements Memento {
    private final DragonCardIterable dragonCardIterable;

    public DragonCardKeeper(CreatureIterable creatureIterable) {
        this.dragonCardIterable = new DragonCardIterable(creatureIterable);
    }

    public DragonCardKeeper(Map<String, Object> saveMap, CreatureIterable creatureIterable) {
        Map<String, Object> dragonCardIterableSaveMap = (Map<String, Object>) saveMap.get("dragonCardIterable");

        this.dragonCardIterable = new DragonCardIterable(dragonCardIterableSaveMap, creatureIterable);
    }

    /**
     * Returns the DragonCardIterable object that represents the collection of DragonCards.
     *
     * @return the DragonCardIterable object
     */
    public DragonCardIterable getDragonCardIterable() {
        return dragonCardIterable;
    }

    /**
     * Overrides the save method to update the given map with the serialized version of the dragonCardIterable.
     *
     * @param  map  a map of String to Object to be updated with the serialized dragonCardIterable
     * @return      the updated map
     */
    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("dragonCardIterable", this.dragonCardIterable.save(new HashMap<>()));
        return map;
    }

}