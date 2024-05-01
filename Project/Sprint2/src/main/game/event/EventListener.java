package main.game.event;

/**
 * The EventListener interface defines a contract for classes that listen for main.game events.
 */
public interface EventListener {
    /**
     * Method called when an event occurs.
     *
     * @param eventType The type of event that occurred.
     */
    void update(EventType eventType);
}
