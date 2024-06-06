package main.game.chit;

import main.game.chit.iterators.DragonCardIterable;
import main.game.creature.iterators.CreatureIterable;
import main.game.snapshot.Memento;

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
    public Map save(Map map) {
        return Map.of();
    }

    @Override
    public Map load(Map map) {
        return Map.of();
    }
}