package main.game.tile;

import main.game.creature.iterators.CreatureIterator;
import main.game.tile.iterators.TileContainingPlayerIterator;
import main.game.tile.iterators.VolcanoTileIterator;

public class TileKeeper {
    private final VolcanoTileIterator volcanoTileIterator;
    private final TileContainingPlayerIterator tileContainingPlayerIterator;

    public TileKeeper(CreatureIterator creatureIterator) {
        this.volcanoTileIterator = new VolcanoTileIterator(creatureIterator);
        this.tileContainingPlayerIterator = new TileContainingPlayerIterator();
    }

    public VolcanoTileIterator getVolcanoTileIterator() {
        this.volcanoTileIterator.reset();
        return this.volcanoTileIterator;
    }

    public TileContainingPlayerIterator getTileContainingPlayerIterator() {
        this.tileContainingPlayerIterator.reset();
        return this.tileContainingPlayerIterator;
    }
}
