package main.game.tile.iterators;

import main.game.creature.iterators.CreatureIterator;
import main.game.tile.TileNode;
import main.game.tile.type.VolcanoTileType;
import main.misc.Settings;

import java.util.Iterator;

public class VolcanoTileIterator implements Iterator<TileNode> {
    private TileNode rootNode;
    private TileNode currNode;

    public VolcanoTileIterator(CreatureIterator creatureIterator){
        constructVolcanoTiles(creatureIterator);
    }

    private void constructVolcanoTiles(CreatureIterator creatureIterator){
        long boardSize = (long) Settings.getSetting("VolcanoTile");


        this.rootNode = new TileNode(new VolcanoTileType(null, creatureIterator.next()), null);


    }

    @Override
    public boolean hasNext() {
        return currNode.getLeft() != this.rootNode;
    }

    @Override
    public TileNode next() {
        return null;
    }

    public void reset(){
        this.currNode = this.rootNode;
    }
}
