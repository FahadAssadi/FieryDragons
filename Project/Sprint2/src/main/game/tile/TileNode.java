package main.game.tile;

import main.exceptions.UndefinedMoveException;
import main.game.player.Player;
import main.game.snapshot.Memento;
import main.game.tile.type.TileType;
import main.misc.Settings;
import main.ui.frames.TicTacToeGameFrame;

import java.util.LinkedHashMap;
import java.util.Map;

public class TileNode implements Memento {
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
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        boardSize++;

        if (steps == 0){
            return this;
        }

        if (totalMoves > boardSize) {
            throw new UndefinedMoveException("Moves overflow the graph");
        } else if (totalMoves == boardSize) {
            return this.getAdjacentTile().traverseForward(--steps, ++totalMoves);
        } else { // totalMoves < boardSize
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

        return this.getPreviousTile().traverseBackward(++steps);
    }

    public void movePlayerToTile(TileNode nextTile, int steps) throws Exception {
        Player currPlayer = this.getType().getPlayer();
        Player adjPlayer = nextTile.getType().getPlayer();

        if (adjPlayer != null) {
            currPlayer = this.resolveCollision(currPlayer, adjPlayer);
        }

        this.getType().setPlayer(null);
        currPlayer.move(steps);
        nextTile.getType().setPlayer(currPlayer);
    }

    public Player resolveCollision(Player player1, Player player2) throws Exception {
        // Currently only throws exception
//        throw new FilledTileException("Player Exists");

        TicTacToeGameFrame ticTacToeGameFrame = new TicTacToeGameFrame(player1, player2);
        return ticTacToeGameFrame.getWinner();
    }

    @Override
    public String toString() {
        return "TileNode{" +
                "tempID=" + tempID +
                ", type=" + type +
                '}';
    }

    @Override
    public Map<String, Object> save(Map<String, Object> map) {
        map.put("ID", this.getTempID());
        map.put("Creature", this.getType().getCreature().getCreatureID());
        Player player = this.getType().getPlayer();

        if (player == null) {
            map.put("Player", null);
        } else {
            map.put("Player", this.getType().getPlayer().save(new LinkedHashMap<>()));
        }

        return map;
    }

    @Override
    public Map<String, Object> load(Map<String, Object> map) {
        return Map.of();
    }
}
