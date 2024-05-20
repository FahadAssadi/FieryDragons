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

    private void createTileableCreatures(CreatureIterable creatureIterable){
        this.tileableCreatures = new ArrayList<>();

        for (Creature creature : creatureIterable) {
            if (creature.isTileable()) {
                this.tileableCreatures.add(creature);
            }
        }
    }

    @Override
    public Iterator<Creature> iterator() {
        return tileableCreatures.iterator();
    }
}
