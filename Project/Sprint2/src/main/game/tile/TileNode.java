package main.game.tile;

import main.exceptions.BackwardsMoveNotAllowedException;
import main.game.tile.type.TileType;
import main.misc.Settings;

public class TileNode {
    private final TileType type;
    private int tempID;

    // Previous tile in the graph
    private TileNode previousVolcanoTile;

    // Follow to reach a volcano tile
    private TileNode nextVolcanoTile;

    // Follow to potentially reach a cave tile
    private TileNode caveTile;

    public TileNode(TileType type, TileNode previousVolcanoTile, int tempID) {
        this.type = type;
        this.previousVolcanoTile = previousVolcanoTile;
        this.tempID = tempID;
    }

    public int getTempID() {
        return tempID;
    }

    public void setPreviousVolcanoTile(TileNode previousVolcanoTile) {
        this.previousVolcanoTile = previousVolcanoTile;
    }

    public void setNextVolcanoTile(TileNode nextVolcanoTile) {
        this.nextVolcanoTile = nextVolcanoTile;
    }

    public void setCaveTile(TileNode caveTile) {
        this.caveTile = caveTile;
    }

    public TileType getType() {
        return type;
    }

    public TileNode getPreviousVolcanoTile() {
        return previousVolcanoTile;
    }

    public TileNode getNextVolcanoTile() {
        return nextVolcanoTile;
    }

    public TileNode getCaveTile() {
        return caveTile;
    }

    public TileNode getNextTile(int steps, int totalMoves) {
        if (steps > 1){
            nextVolcanoTile = this.getNextVolcanoTile();
            return nextVolcanoTile.getNextTile(steps - 1, totalMoves + 1);
        } else {
            long boardSize = (long) Settings.getSetting("VolcanoTile");
            if (totalMoves == ++boardSize) {
                return getCaveTile();
            } else {
                return this.getNextVolcanoTile();
            }
        }

    }
    /**
     Steps: given steps to be negative.
     */
    public TileNode traverseBackwards(int steps) throws Exception {
        if (this.previousVolcanoTile == null) {
            throw new BackwardsMoveNotAllowedException("You are in a cave");
        }
        if (steps == 0) {
            return this;
        }

        return this.previousVolcanoTile.traverseBackwards(steps + 1);
    }
}
