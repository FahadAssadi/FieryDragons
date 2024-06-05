package main.game.tile;

import main.misc.Settings;

import java.util.Iterator;

/**
 * Represents a linked list of volcano cards, each containing a chain of tile nodes.
 * This class also provides an iterator to traverse through the nodes of a single card.
 */
public class VolcanoCard implements Iterable<TileNode>{
    private final TileNode startTileNode;
    private VolcanoCard nextVolcanoCard;

    /**
     * Constructs a new VolcanoCard with the specified starting tile node.
     *
     * @param tileNode the starting node of this volcano card
     */
    public VolcanoCard(TileNode tileNode) {
        this.startTileNode = tileNode;
    }

    /**
     * Returns the starting tile node of this volcano card.
     *
     * @return the starting tile node
     */
    public TileNode getStartTileNode() {
        return startTileNode;
    }

    /**
     * Returns the next volcano card in the linked list.
     *
     * @return the next volcano card, or null if there is none
     */
    public VolcanoCard getNextVolcanoCard() {
        return nextVolcanoCard;
    }


    /**
     * Sets the next volcano card in the linked list.
     *
     * @param nextVolcanoCard the next volcano card to set
     */
    public void setNextVolcanoCard(VolcanoCard nextVolcanoCard) {
        this.nextVolcanoCard = nextVolcanoCard;
    }

    /**
     * Returns an iterator over the tile nodes in this volcano card.
     * The iterator traverses nodes starting from the startTileNode and follows their next links.
     *
     * @return an iterator of TileNode
     */
    @Override
    public Iterator<TileNode> iterator() {
        return new volcanoCardIterator();
    }

    /**
     * Internal iterator to navigate through the TileNodes of a VolcanoCard.
     */
    private class volcanoCardIterator implements Iterator<TileNode> {
        private TileNode currNode = startTileNode;
        private boolean firstCall = true;
        long totalNumAnimals = (long) Settings.getSetting("VolcanoCardAnimals");

        /**
         * Determines if there is a next node available in the iteration sequence.
         *
         * @return true if there is a next node, false otherwise
         */
        @Override
        public boolean hasNext() {
            // If it's the first call, we definitely have a next node (the start node)
            if (firstCall) {
                return true;
            }
            // If the current node is null, there are no more nodes
            if (currNode == null) {
                return false;
            }
            // Check if there is a next tile
            if (currNode.getNextTile() != null) {
                // if there is another volcano card then make sure that this next tile does not match first tile of that volcano card
                // as that indicates you are at the end of the volcano card.
                if (nextVolcanoCard != null) {
                    return nextVolcanoCard.getStartTileNode() != currNode.getNextTile();
                }
                // if this is the final volcano card, only iterate as much as the num of animals that volcano cards have.
                if (nextVolcanoCard == null) {
                    totalNumAnimals --;

                    if (totalNumAnimals == 0) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns the next node in the iteration sequence.
         *
         * @return the next TileNode
         */
        @Override
        public TileNode next() {
            if (firstCall) {
                firstCall = false;
            } else {
                currNode = currNode.getNextTile();
            }
            return currNode;
        }
    }
}