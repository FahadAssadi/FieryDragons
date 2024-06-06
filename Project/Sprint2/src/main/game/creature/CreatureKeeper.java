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

    /**
     * Returns the CreatureIterable object that allows iteration over all the creatures in the game.
     *
     * @return the CreatureIterable object
     */
    public CreatureIterable getCreatureIterable() {
        return creatureIterable;
    }

    /**
     * Returns the TileableCreatureIterable object that allows iteration over all the creatures on tiles in the game.
     *
     * @return the TileableCreatureIterable object for tileable creatures
     */
    public TileableCreatureIterable getTileableCreatureIterable() {
        return tileableCreatureIterable;
    }
}
