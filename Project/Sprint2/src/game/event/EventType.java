package game.event;

/**
 * The EventType enum represents different types of game events.
 */
public enum EventType {
    /**
     * Indicates the start of a player's turn.
     */
    PLAYER_TURN_START,

    /**
     * Indicates the end of a player's turn.
     */
    PLAYER_TURN_END,

    /**
     * Indicates the event to shuffle cards.
     */
    SHUFFLE_CARDS,

    /**
     * Indicates the game over event.
     */
    GAME_OVER
}
