package main.game.tile.iterators;

import main.game.tile.TileNode;

import java.util.Iterator;

public class TileContainingPlayerIterator implements Iterator<TileNode> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public TileNode next() {
        return null;
    }

    public void reset() {

    }
}
