package main.game.creature;

import main.game.creature.iterators.CreatureIterable;
import main.game.creature.iterators.TileableCreatureIterable;

public class CreatureKeeper {
    private final CreatureIterable creatureIterable;
    private final TileableCreatureIterable tileableCreatureIterable;


    public CreatureKeeper() {
        this.creatureIterable = new CreatureIterable();
        this.tileableCreatureIterable = new TileableCreatureIterable(creatureIterable);
    }

    public CreatureIterable getCreatureIterable() {
        return creatureIterable;
    }

    public TileableCreatureIterable getTileableCreatureIterable() {
        return tileableCreatureIterable;
    }
}
