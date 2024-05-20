package main.game.chit;

import main.game.chit.iterators.DragonCardIterable;
import main.game.creature.iterators.CreatureIterable;

public class DragonCardKeeper {
    private final DragonCardIterable dragonCardIterable;
    public DragonCardKeeper(CreatureIterable creatureIterable) {
        this.dragonCardIterable = new DragonCardIterable(creatureIterable);
    }
    public DragonCardIterable getDragonCardIterable() {
        return dragonCardIterable;
    }


}