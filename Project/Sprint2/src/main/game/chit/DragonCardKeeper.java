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

    public DragonCardIterable getDragonCardIterable() {
        return dragonCardIterable;
    }

    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        map.put("dragonCardIterable", this.dragonCardIterable.save(new HashMap<>()));
        return map;
    }

}