package main.game.event;

/**
 * The EventType enum represents different types of game events.
 */
public enum EventType {
    /**
     * Indicates the end of a player's turn.
     */
    PLAYER_TURN_END,

    /**
     * Indicates the event to move a player.
     */
    PLAYER_MOVED,

    /**
     * Indicates the game over event.
     */
    GAME_OVER,

    /**
     * Indicates the event to save the game.
     */
    SAVE,

    /**
     * Indicates the event to load the game.
     */
    LOAD
}
