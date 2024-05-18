package main.game.tile.iterators;

import main.game.creature.Creature;
import main.game.creature.iterators.TileableCreatureIterable;
import main.game.tile.TileNode;
import main.game.tile.type.VolcanoTileType;
import main.misc.Settings;

import javax.swing.*;
import java.util.Iterator;

public class VolcanoTileIterable implements Iterable<TileNode> {
    private TileNode rootNode;

    // Path to the directory containing creature images
    private static final String CREATURE_FILE_PATH = "/assets/pngs/creatures/";

    public VolcanoTileIterable(TileableCreatureIterable tileableCreatureIterable) {
        constructVolcanoTiles(tileableCreatureIterable);
    }

    private void constructVolcanoTiles(TileableCreatureIterable tileableCreatureIterable) {
        long boardSize = (long) Settings.getSetting("VolcanoTile");

        Iterator<Creature> tileableCreatureIterator = tileableCreatureIterable.iterator();

        TileNode prevNode = null;

        int counter = 0;

        // Setting the children links
        while (counter < boardSize) {
            if (!tileableCreatureIterator.hasNext()) {
                tileableCreatureIterator = tileableCreatureIterable.iterator(); // Reset the iterator
            }

            Creature currCreature = tileableCreatureIterator.next();
            String imagePath = CREATURE_FILE_PATH + currCreature.getCreatureName() + "_1.png";
            ImageIcon volcanoTileImage = new ImageIcon(getClass().getResource(imagePath));

            TileNode currNode = new TileNode(new VolcanoTileType(volcanoTileImage, currCreature), prevNode, counter);

            // Only occurs during the first iteration
            if (prevNode != null) {
                prevNode.setLeft(currNode);
                currNode.setParent(prevNode);
            } else {
                this.rootNode = currNode; // Set the first node as the rootNode
            }

            prevNode = currNode;

            counter++;
        }

        // Set the parent link for the rootNode
        prevNode.setLeft(this.rootNode);
        this.rootNode.setParent(prevNode);
    }

    @Override
    public Iterator<TileNode> iterator() {
        return new VolcanoTileIterator();
    }

    private class VolcanoTileIterator implements Iterator<TileNode> {
        private TileNode currNode = rootNode;

        @Override
        public boolean hasNext() {
            return currNode.getLeft() != null;
        }

        @Override
        public TileNode next() {
            TileNode nextNode = currNode;
            currNode = currNode.getLeft();

            return nextNode;
        }
    }
}