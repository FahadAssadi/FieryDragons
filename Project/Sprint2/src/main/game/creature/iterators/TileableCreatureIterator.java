package main.game.creature.iterators;

import main.game.creature.Creature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileableCreatureIterator implements Iterator<Creature> {
    private List<Creature> tileableCreatures;
    private int currentIndex;

    public TileableCreatureIterator(CreatureIterator creatureIterator) {
        this.createTileableCreatures(creatureIterator);
        this.currentIndex = 0;
    }

    private void createTileableCreatures(CreatureIterator creatureIterator){
        this.tileableCreatures = new ArrayList<>();
        creatureIterator.reset();

        while (creatureIterator.hasNext()){
            Creature creature = creatureIterator.next();

            if (creature.isTileable()){
                this.tileableCreatures.add(creature);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex < this.tileableCreatures.size();
    }

    @Override
    public Creature next() {
        return this.tileableCreatures.get(this.currentIndex++);
    }

    public void reset() {
        this.currentIndex = 0;
    }
}
