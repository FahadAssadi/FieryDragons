package main.game.tile;

import main.exceptions.FilledTileException;
import main.exceptions.UndefinedMoveException;
import main.game.player.Player;
import main.game.tile.type.TileType;

public class TileNode {
    private final TileType type;
    private int tempID;

    /*
    The structure of a tile will look something like this:

                        ADJACENT_TILE
                              |
               PREV_TILE --- this --- NEXT_TILE

     */

    // Previous tile in the graph
    private TileNode previousTile;

    // Follow to reach a volcano tile
    private TileNode nextTile;

    // Follow to potentially reach a cave tile
    private TileNode adjacentTile;

    public TileNode(TileType type, TileNode previousTile, int tempID) {
        this.type = type;
        this.previousTile = previousTile;
        this.tempID = tempID;
    }

    public int getTempID() {
        return tempID;
    }

    public void setPreviousTile(TileNode previousTile) {
        this.previousTile = previousTile;
    }

    public void setNextTile(TileNode nextTile) {
        this.nextTile = nextTile;
    }

    public void setAdjacentTile(TileNode adjacentTile) {
        this.adjacentTile = adjacentTile;
    }

    public TileType getType() {
        return type;
    }

    public TileNode getPreviousTile() {
        return previousTile;
    }

    public TileNode getNextTile() {
        return nextTile;
    }

    public TileNode getAdjacentTile() {
        return adjacentTile;
    }

    public TileNode traverseForward(int steps, int totalMoves) throws Exception {
        if (steps == 0){
            return this;
        }

        if (totalMoves > 25) {
            throw new UndefinedMoveException("Moves overflow the graph");
        } else if (totalMoves == 25) {
            return this.getAdjacentTile().traverseForward(--steps, ++totalMoves);
        } else {
            return this.getNextTile().traverseForward(--steps, ++totalMoves);
        }

    }

    /**
     Steps: given steps to be negative.
     */
    public TileNode traverseBackward(int steps) throws Exception {
        if (steps == 0) {
            return this;
        }

        if (this.previousTile == null) {
            throw new UndefinedMoveException("No previous tile before cave");
        }

        return this.previousTile.traverseBackward(++steps);
    }

    public void movePlayerToTile(TileNode nextTile, int steps) throws Exception {
        if (nextTile.getType().getPlayer() != null) {
            throw new FilledTileException("Player Exists");
        }

        Player player = this.getType().getPlayer();
        this.getType().setPlayer(null);

        player.move(steps);
        nextTile.getType().setPlayer(player);
    }
}
