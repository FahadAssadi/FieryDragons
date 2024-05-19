package main.game.player;

import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.behaviour.BehaviourStrategy;
import main.misc.Settings;

/**
 * The Player class represents a player in the main.game.
 * It contains information such as behaviour, position, and total moves of the player.
 */
public class Player {
    // The behaviour strategy of the player
    private final BehaviourStrategy behaviour;

    // The colour associated with the player
    private final String colour;

    // The total number of moves made by the player
    private int totalMoves;

    // Default value for total moves
    private final static int DEFAULT_TOTAL_MOVES = 0;

    /**
     * Constructs a Player object with the specified behaviour strategy, starting position, and colour.
     *
     * @param behaviour     The behaviour strategy of the player.
     * @param colour        The colour associated with the player.
     */
    public Player(BehaviourStrategy behaviour, String colour) {
        this.behaviour = behaviour;
        this.colour = colour;
        this.totalMoves = DEFAULT_TOTAL_MOVES;
    }

    public void move(int steps) {
        this.totalMoves += steps;

        if (this.totalMoves == (long) Settings.getSetting("VolcanoTile") + 2) {
            EventManager.getInstance().notify(EventType.GAME_OVER);
        }
    }

    /**
     * Retrieves the colour associated with the player.
     *
     * @return The colour of the player.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Retrieves a string representation of the player, including the colour and position.
     *
     * @return A string representation of the player.
     */
    @Override
    public String toString() {
        return colour + " " + totalMoves;
    }

    public int getTotalMoves() {
        return totalMoves;
    }
}
