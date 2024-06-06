package main.game.player;

import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.behaviour.BehaviourStrategy;
import main.game.snapshot.Memento;
import main.misc.Settings;

import java.util.Map;

/**
 * The Player class represents a player in the main.game.
 * It contains information such as behaviour, position, and total moves of the player.
 */
public class Player implements EventListener, Memento {
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

        EventManager.getInstance().subscribe(EventType.PLAYER_MOVED, this);
    }

    /**
     * Increases the total number of moves made by the player by the specified number of steps.
     *
     * @param  steps  the number of steps to add to the total number of moves
     */
    public void move(int steps) {
        this.totalMoves += steps;
    }

    /**
     * Checks if the player has won the game by determining if the total number of moves made by the player
     * is equal to the board size plus two.
     *
     * @return true if the player has won, false otherwise
     */
    public boolean hasWon() {
        long boardSize = (long) Settings.getSetting("VolcanoTile");

        return this.totalMoves == boardSize + 2;
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

    /**
     * Gets the total number of moves.
     *
     * @return         the total number of moves
     */
    public int getTotalMoves() {
        return totalMoves;
    }

    /**
     * Updates the game state based on the event type provided.
     *
     * @param  eventType  the type of event triggering the update
     */
    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_MOVED) {
            if (hasWon()){
                EventManager.getInstance().notify(EventType.GAME_OVER);
            }
        }

    }

    /**
     * Saves the player's colour and total moves into a map.
     *
     * @param  map  the map to store the player's data
     * @return      the same map with the player's data added
     */
    @Override
    public Map<String, Object> save(Map<String, Object> map) {
        map.put("colour", this.getColour());
        map.put("totalMoves", this.getTotalMoves());
        return map;
    }

}
