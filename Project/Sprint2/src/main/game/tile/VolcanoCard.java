package main.game.tile;

import main.misc.Settings;

import java.util.Iterator;

public class VolcanoCard implements Iterable<TileNode>{
    private final TileNode startTileNode;
    private VolcanoCard nextVolcanoCard;

    public VolcanoCard(TileNode tileNode) {
        this.startTileNode = tileNode;
    }

    public TileNode getStartTileNode() {
        return startTileNode;
    }

    public VolcanoCard getNextVolcanoCard() {
        return nextVolcanoCard;
    }

    public void setNextVolcanoCard(VolcanoCard nextVolcanoCard) {
        this.nextVolcanoCard = nextVolcanoCard;
    }

    @Override
    public Iterator<TileNode> iterator() {
        return new volcanoCardIterator();
    }

    private class volcanoCardIterator implements Iterator<TileNode> {
        private TileNode currNode = startTileNode;
        private boolean firstCall = true;
        long totalNumAnimals = (long) Settings.getSetting("VolcanoCardAnimals");
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