package main.game.tile;

import main.exceptions.FilledTileException;
import main.exceptions.PlayerSwappedException;
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

    /**
     * Returns the temporary ID of the TileNode.
     *
     * @return the temporary ID of the TileNode
     */
    public int getTempID() {
        return tempID;
    }

    /**
     * Sets the previous tile of this TileNode.
     *
     * @param  previousTile  the new previous tile
     */
    public void setPreviousTile(TileNode previousTile) {
        this.previousTile = previousTile;
    }

    /**
     * Sets the next tile of this TileNode.
     *
     * @param  nextTile  the new next tile
     */
    public void setNextTile(TileNode nextTile) {
        this.nextTile = nextTile;
    }

    /**
     * Sets the adjacent tile of this TileNode.
     *
     * @param  adjacentTile  the new adjacent tile
     */
    public void setAdjacentTile(TileNode adjacentTile) {
        this.adjacentTile = adjacentTile;
    }

    /**
     * Returns the type of the TileNode.
     *
     * @return the type of the TileNode
     */
    public TileType getType() {
        return type;
    }

    /**
     * Returns the previous tile of this TileNode.
     *
     * @return the previous tile of this TileNode
     */
    public TileNode getPreviousTile() {
        return previousTile;
    }

    /**
     * Returns the next tile of this TileNode.
     *
     * @return the next tile of this TileNode
     */
    public TileNode getNextTile() {
        return nextTile;
    }

    /**
     * Returns the adjacent tile of the current tile.
     *
     * @return the adjacent tile
     */
    public TileNode getAdjacentTile() {
        return adjacentTile;
    }

    /**
     * A method to traverse forward in the TileNode graph based on the given steps and total moves.
     *
     * @param  steps      the number of steps to traverse forward
     * @param  totalMoves the total number of moves taken so far
     * @return            the TileNode after traversing forward
     * @throws UndefinedMoveException if the moves overflow the graph
     */
    public TileNode traverseForward(int steps, int totalMoves) throws UndefinedMoveException {
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

    /**
     * Moves the player to the specified tile after resolving any collisions with other players.
     *
     * @param  nextTile  the tile to move the player to
     * @param  steps     the number of steps to move the player
     * @throws FilledTileException    if there is a player on the adjacent tile
     * @throws PlayerSwappedException if the players are swapped after resolving the collision
     */
    public void movePlayerToTile(TileNode nextTile, int steps) throws FilledTileException, PlayerSwappedException {
        Player currPlayer = this.getType().getPlayer();
        Player adjPlayer = nextTile.getType().getPlayer();

        if (adjPlayer != null) {
            Player winner = this.resolveCollision(currPlayer, adjPlayer);
            if (winner != currPlayer) {
                throw new FilledTileException("Player Exists");
            } else {
                // swap players
                int difference = findTileDifference(this, nextTile, 0);

                nextTile.getType().setPlayer(currPlayer);
                this.getType().setPlayer(adjPlayer);

                currPlayer.move(difference);
                adjPlayer.move(difference * - 1);

                throw new PlayerSwappedException("Player swapped");
            }
        }

        this.getType().setPlayer(null);
        currPlayer.move(steps);
        nextTile.getType().setPlayer(currPlayer);
    }

    /**
     * Resolves a collision between two players by creating a TicTacToeGameFrame and returning the winner.
     *
     * @param  player1 the first player in the collision
     * @param  player2 the second player in the collision
     * @return         the winner of the TicTacToeGameFrame
     */
    public Player resolveCollision(Player player1, Player player2) {
        // Currently only throws exception
//        throw new FilledTileException("Player Exists");

        TicTacToeGameFrame ticTacToeGameFrame = new TicTacToeGameFrame(player1, player2);
        return ticTacToeGameFrame.getWinner();
    }

    /**
     * Recursively finds the difference in steps between two given tiles.
     *
     * @param  currentTile   the current tile to compare
     * @param  targetTile    the target tile to find the difference to
     * @param  currentSteps  the current number of steps taken
     * @return               the difference in steps between the two tiles
     */
    public int findTileDifference(TileNode currentTile, TileNode targetTile, int currentSteps) {
        if (currentTile.equals(targetTile)) {
            return currentSteps;
        }

        return findTileDifference(currentTile.getNextTile(), targetTile, currentSteps + 1);
    }

    /**
     * Returns a string representation of the TileNode object.
     *
     * @return          a string representation of the TileNode object
     */
    @Override
    public String toString() {
        return "TileNode{" +
                "tempID=" + tempID +
                ", type=" + type +
                '}';
    }

    /**
     * Saves the state of the TileNode into a map.
     *
     * @param  map  the map to store the state of the TileNode
     * @return     the map containing the saved state
     */
    @Override
    public Map<String, Object> save(Map<String, Object> map) {
        map.put("Creature", this.getType().getCreature().getCreatureID());

        TileNode CaveTile = this.getAdjacentTile();

        if (CaveTile == null) {
            map.put("Cave", null);
        } else {
            map.put("Cave", this.getAdjacentTile().getTempID());
        }


        return map;
    }

}
