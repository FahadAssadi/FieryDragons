package main.game.creature.iterators;

import main.game.creature.Creature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileableCreatureIterable implements Iterable<Creature> {
    private List<Creature> tileableCreatures;

    public TileableCreatureIterable(CreatureIterable creatureIterable) {
        this.createTileableCreatures(creatureIterable);
    }

    /**
     * Creates a list of tileable creatures from the given creature iterable.
     *
     * @param  creatureIterable  the iterable of creatures to create tileable creatures from
     */
    private void createTileableCreatures(CreatureIterable creatureIterable){
        this.tileableCreatures = new ArrayList<>();

        for (Creature creature : creatureIterable) {
            if (creature.isTileable()) {
                this.tileableCreatures.add(creature);
            }
        }
    }

    /**
     * Returns an iterator over the elements in this TileableCreatureIterable.
     *
     * @return  an Iterator object for iterating over the elements in this TileableCreatureIterable
     */
    @Override
    public Iterator<Creature> iterator() {
        return tileableCreatures.iterator();
    }
}
